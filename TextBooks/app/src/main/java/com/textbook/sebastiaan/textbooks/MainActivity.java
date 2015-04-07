package com.textbook.sebastiaan.textbooks;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity{

    private DataSource datasource;

    private EditText bookName, authorName;
    private Button addBook, showBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new DataSource(this);
        bookName = (EditText) findViewById(R.id.add_book_edittext);
        authorName = (EditText) findViewById(R.id.add_author_edittext);

        addBook = (Button) findViewById(R.id.add_book_button);
        showBooks = (Button) findViewById(R.id.show_book_button);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book = bookName.getText().toString();
                String author = bookName.getText().toString();
                if(!book.matches("") && !author.matches("")) {
                    long assignmentId = datasource.createAssignment(bookName.getText().toString(), authorName.getText().toString());
                    if(assignmentId != -1)
                    {
                        Assignment assignment = datasource.getAssignment(assignmentId);
                        ShowBooks.assignmentArrayAdapter.add(assignment);
                        Toast toast = Toast.makeText(getApplicationContext(), "The book has been added", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    bookName.setText("");
                    authorName.setText("");
                }
            }
        });

        showBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowBooks.class);
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
}