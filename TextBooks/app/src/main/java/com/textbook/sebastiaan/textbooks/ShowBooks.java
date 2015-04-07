package com.textbook.sebastiaan.textbooks;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class ShowBooks extends ActionBarActivity {

    private ListView listView;
    public static final String EXTRA_ASSIGNMENT_ID = "extraAssignmentId";
    public static ArrayAdapter<Assignment> assignmentArrayAdapter;
    private DataSource datasource;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);

        listView = (ListView)findViewById(R.id.book_list);
        TextView emptyView = (TextView)findViewById(R.id.book_list_empty);
        listView.setEmptyView(emptyView);

        datasource = new DataSource(this);
        List<Assignment> assignments = datasource.getAllAssignments();
        assignmentArrayAdapter = new ArrayAdapter<Assignment>(this, android.R.layout.simple_list_item_1, assignments);
        listView.setAdapter(assignmentArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
