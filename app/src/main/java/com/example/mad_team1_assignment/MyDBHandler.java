package com.example.mad_team1_assignment;
//SQLite DB

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="story.db";
    public static final String TABLE_STORYTEXT="storytext";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TEXT = "text";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_STORY_TABLE = "CREATE TABLE " + TABLE_STORYTEXT + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TEXT  + " TEXT )";
        db.execSQL(CREATE_STORY_TABLE);
    }
    public void addText(StorySQLite story){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEXT, story.getStoryText());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_STORYTEXT,null,values);
        db.close();
    }



}

