package com.example.sebastiaan.minecraftbuildchallenge;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends ActionBarActivity {

    private DataSource datasource;
    private TextView buildTextView, blockTextView, biomeTextView;
    private Assignment assignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        datasource = new DataSource(this);
        long assignmentId = getIntent().getLongExtra(MainActivity.EXTRA_ASSIGNMENT_ID, -1);
        assignment = datasource.getAssignment(assignmentId);
        buildTextView = (TextView) findViewById(R.id.build_textview);
        buildTextView.setText(assignment.getBuild());
        blockTextView = (TextView) findViewById(R.id.block_textview);
        blockTextView.setText(assignment.getBlock());
        biomeTextView = (TextView) findViewById(R.id.biome_textview);
        biomeTextView.setText(assignment.getBiome());
    }
}
