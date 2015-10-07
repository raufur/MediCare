package com.coders.epsilon.medicare.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.coders.epsilon.medicare.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobile App Develop on 27-6-15.
 */
public class NoteDataSource {
    // Database fields
    private SQLiteDatabase iCareDatabase;
    private SQLiteHelper sqlHelper;
    List<Note> noteList = new ArrayList<Note>();

    public NoteDataSource(Context context) {
        sqlHelper = new SQLiteHelper(context);
    }

    /*
     * open a method for writable database
     */
    public void open() throws SQLException {
        iCareDatabase = sqlHelper.getWritableDatabase();
    }

    public void read() throws SQLException {
        iCareDatabase = sqlHelper.getReadableDatabase();
    }
    /*
     * close database connection
     */
    public void close() {
        sqlHelper.close();
    }

	/*
	 * insert data into the database.
	 */

    public boolean insert(Note note) {

        this.open();

        ContentValues cvInsert = new ContentValues();

        cvInsert.put(SQLiteHelper.COL_NOTE_TBL_NAME,
                note.getNotename());

        cvInsert.put(SQLiteHelper.COL_NOTE_TBL_DETAIL,
                note.getNotedetail());



        long check = iCareDatabase.insert(
                SQLiteHelper.I_CARE_NOTE_TBL, null, cvInsert);
        this.close();
        if (check < 0)
            return false;
        else
            return true;
    }

    // Updating database by id
    public boolean updateData(long noteId, Note note) {
        this.open();
        ContentValues cvUpdate = new ContentValues();

        cvUpdate.put(SQLiteHelper.COL_NOTE_TBL_NAME,
                note.getNotename());

        cvUpdate.put(SQLiteHelper.COL_NOTE_TBL_DETAIL,
                note.getNotedetail());

        int check = iCareDatabase.update(SQLiteHelper.I_CARE_NOTE_TBL,
                cvUpdate, SQLiteHelper.COL_NOTE_TBL_ID + "="
                        + noteId, null);

        this.close();
        if (check == 0)
            return false;
        else
            return true;
    }

    // Getting Note
    public List<Note> getNoteList() {
        List<Note> NoteList = new ArrayList<Note>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SQLiteHelper.I_CARE_NOTE_TBL;


        iCareDatabase = sqlHelper.getWritableDatabase();
        Cursor cursor = iCareDatabase .rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId((cursor.getString(0)));
                note.setNotename(cursor.getString(1));
                note.setNotedetail(cursor.getString(2));


                // Adding contact to list
                NoteList.add(note);
            } while (cursor.moveToNext());
        }

        // return contact list
        return NoteList;
    }

}


