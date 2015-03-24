package com.example.sebastiaan.minecraftbuildchallenge;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    private ListView listView;
    public static final String EXTRA_ASSIGNMENT = "extraAssignment";
    public static final String EXTRA_ASSIGNMENT_ID = "extraAssignmentId";
    private ArrayAdapter<Assignment> assignmentArrayAdapter;
    private DataSource datasource;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.main_list);
        TextView emptyView = (TextView)findViewById(R.id.main_list_empty);
        listView.setEmptyView(emptyView);

        datasource = new DataSource(this);
        List<Assignment> assignments = datasource.getAllAssignments();
        assignmentArrayAdapter = new ArrayAdapter<Assignment>(this, android.R.layout.simple_list_item_1, assignments);
        listView.setAdapter(assignmentArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long
                    id)
            {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(EXTRA_ASSIGNMENT_ID, assignmentArrayAdapter.getItem(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_menu_add)
        {
            startActivityForResult(new Intent(this, AddAssignmentActivity.class), 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                long assignmentId = data.getLongExtra(EXTRA_ASSIGNMENT_ID, -1);
                if(assignmentId != -1)
                {
                    Assignment assignment = datasource.getAssignment(assignmentId);
                    assignmentArrayAdapter.add(assignment);
                }
            }
        }
    }
}
