package com.example.mad_team1_assignment;
//SQLite DB

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
        String CREATE_STORY_TABLE = "CREATE TABLE " + TABLE_STORYTEXT + "(" + COLUMN_ID + " TEXT," + COLUMN_TEXT  + " TEXT"+ ")";
        db.execSQL(CREATE_STORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORYTEXT);
        onCreate(db);
    }

    public void addText(StorySQLite story){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEXT, story.getStoryText());
        values.put(COLUMN_ID, story.getTextID());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_STORYTEXT,null,values);
        db.close();
    }

    public StorySQLite getStory(int lineNumber){
        String query ="SELECT * FROM " + TABLE_STORYTEXT + " WHERE " + COLUMN_ID + " = \"" + lineNumber + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        StorySQLite queryData = new StorySQLite();
        if(cursor.moveToFirst()){
            queryData.setTextID(cursor.getInt(0));
            queryData.setStoryText(cursor.getString(1));
            cursor.close();
        }
        else {
            queryData = null;
        }
        db.close();
        return queryData;
    }
}

