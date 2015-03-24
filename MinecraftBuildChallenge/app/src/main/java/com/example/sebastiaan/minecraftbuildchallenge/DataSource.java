package com.example.sebastiaan.minecraftbuildchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] assignmentAllColumns = { MySQLiteHelper.COLUMN_ASSIGNMENT_ID, MySQLiteHelper.COLUMN_BUILD, MySQLiteHelper.COLUMN_BLOCK, MySQLiteHelper.COLUMN_BIOME };

    public DataSource(Context context)
    {
        dbHelper = new MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
        dbHelper.close();
    }
    // Opens the database to use it
    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }
    // Closes the database when you no longer need it
    public void close()
    {
        dbHelper.close();
    }

    public long createAssignment(String build, String block, String biome)
    {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_BUILD, build);
        values.put(MySQLiteHelper.COLUMN_BLOCK, block);
        values.put(MySQLiteHelper.COLUMN_BIOME, biome);
        long insertId = database.insert(MySQLiteHelper.TABLE_ASSIGNMENTS, null, values);
        // If the database is open, close it
        if (database.isOpen())
            close();
        return insertId;
    }

    public void deleteAssignment(Assignment assignment)
    {
        if (!database.isOpen())
            open();

        database.delete(MySQLiteHelper.TABLE_ASSIGNMENTS, MySQLiteHelper.COLUMN_ASSIGNMENT_ID + " =?", new String[] {Long.toString(assignment.getId())});

        if (database.isOpen())
            close();
    }

    public void updateAssignment(Assignment assignment)
    {
        if (!database.isOpen())
            open();

        ContentValues args = new ContentValues();
        args.put(MySQLiteHelper.COLUMN_BUILD, assignment.getBuild());
        args.put(MySQLiteHelper.COLUMN_BLOCK, assignment.getBlock());
        args.put(MySQLiteHelper.COLUMN_BIOME, assignment.getBiome());
        database.update(MySQLiteHelper.TABLE_ASSIGNMENTS, args, MySQLiteHelper.COLUMN_ASSIGNMENT_ID + "=?", new String[] { Long.toString(assignment.getId())});

        if (database.isOpen())
            close();
    }

    public List<Assignment> getAllAssignments()
    {
        if (!database.isOpen())
            open();

        List<Assignment> assignments = new ArrayList<Assignment>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ASSIGNMENTS, assignmentAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Assignment assignment = cursorToAssignment(cursor);
            assignments.add(assignment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        if (database.isOpen())
            close();

        return assignments;
    }

    public Assignment getAssignment(long columnId)
    {
        if (!database.isOpen())
            open();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ASSIGNMENTS, assignmentAllColumns, MySQLiteHelper.COLUMN_ASSIGNMENT_ID + "=?", new String[] {Long.toString(columnId)}, null, null, null);
        cursor.moveToFirst();
        Assignment assignment = cursorToAssignment(cursor);
        cursor.close();
        if (database.isOpen())
            close();
        return assignment;
    }

    private Assignment cursorToAssignment(Cursor cursor)
    {
        try
        {
            Assignment assignment = new Assignment();
            assignment.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_ASSIGNMENT_ID)));
            assignment.setBuild(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_BUILD)));
            assignment.setBlock(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_BLOCK)));
            assignment.setBiome(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_BIOME)));
            return assignment;
        }catch(CursorIndexOutOfBoundsException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }

}