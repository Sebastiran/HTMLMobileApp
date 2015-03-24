package com.example.sebastiaan.minecraftbuildchallenge;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddAssignmentActivity extends ActionBarActivity{

    private EditText buildEditText, blockEditText, biomeEditText;
    private DataSource datasource;

    private TextView challenge;
    private Button accept, deny;
    private String build, block, biome;
    private List<String> builds = new ArrayList<String>();
    private List<String> blocks = new ArrayList<String>();
    private List<String> biomes = new ArrayList<String>();

    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        datasource = new DataSource(this);
        buildEditText = (EditText) findViewById(R.id.add_build_edittext);
        blockEditText = (EditText) findViewById(R.id.add_block_edittext);
        biomeEditText = (EditText) findViewById(R.id.add_biome_edittext);

        challenge = (TextView) findViewById(R.id.add_assignment_challenge);
        accept = (Button) findViewById(R.id.add_assignment_challenge_accept_button);
        deny = (Button) findViewById(R.id.add_assignment_challenge_deny_button);

        buildsInit();
        blocksInit();
        biomesInit();
        setChallenge();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long assignmentId = datasource.createAssignment(build, block, biome);
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.EXTRA_ASSIGNMENT_ID, assignmentId);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChallenge();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_assignment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_assignment_menu_save)
        {
            long assignmentId = datasource.createAssignment(buildEditText.getText().toString(), blockEditText.getText().toString(), biomeEditText.getText().toString());
            Intent resultIntent = new Intent();
            resultIntent.putExtra(MainActivity.EXTRA_ASSIGNMENT_ID, assignmentId);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setChallenge()
    {
        build = builds.get(r.nextInt(builds.size()));
        block = blocks.get(r.nextInt(blocks.size()));
        biome = biomes.get(r.nextInt(biomes.size()));
        challenge.setText("Build a(n) " + build + " With " + block + " in the " + biome);
    }
    private void buildsInit()
    {
        builds.add("Boat");
        builds.add("Park");
        builds.add("Tree");
        builds.add("Car");
    }
    private void blocksInit()
    {
        blocks.add("Birch Wood");
        blocks.add("CobbleStone");
        blocks.add("Prismarine");
        blocks.add("Diorite");
        blocks.add("Diamond");
    }
    private void biomesInit()
    {
        biomes.add("Ocean");
        biomes.add("Desert");
        biomes.add("Plains");
        biomes.add("Mesa");
        biomes.add("Forest");
        biomes.add("Jungle");
    }
}
