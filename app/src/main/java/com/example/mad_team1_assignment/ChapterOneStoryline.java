package com.example.mad_team1_assignment;

import android.content.Context;
import android.service.autofill.UserData;
import android.util.Log;

import java.util.ArrayList;

public class ChapterOneStoryline {
    private final String TAG = "Adding Chapter 1: ";

    ArrayList<String> listOfText = new ArrayList<>();

    public void AddingStory(Context context){
        MyDBHandler dbHandler = new MyDBHandler(context,null,null,1);
        listOfText.add("Reading Line 1");
        listOfText.add("Reading Line 2");

        for (int i = 0; i < listOfText.size(); i++){
            String dbText = listOfText.get(i);
            Log.v(TAG, listOfText.get(i));
            StorySQLite dbStory = new StorySQLite();
            dbStory.setStoryText(dbText);
            dbStory.setTextID(i);

            dbHandler.addText(dbStory);
        }
    }
}