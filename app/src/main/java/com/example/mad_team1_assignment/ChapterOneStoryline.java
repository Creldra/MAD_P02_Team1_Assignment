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
        listOfText.add("Mary: Where am I? I think I lost my way...");
        listOfText.add("Mary: Is that a Fox over there?");
        listOfText.add("???: Who are you?");
        listOfText.add("Mary: I’m Mary and I am lost.");
        listOfText.add("Norah: I’m Norah, don’t worry I will help you. I have a friend who knows the forest well.");
        listOfText.add("???: Hey Norah, long time no see. Who is this kid?");
        listOfText.add("Norah: Let me introduce you to Max the Monkey.");
        listOfText.add("Max: Hi, nice to meet you, I am Max the Monkey who knows all about the forest.");
        listOfText.add("Mary: Hi, my name is Mary, nice to meet you.");
        listOfText.add("*Norah starts to explain Mary's situation to Max*");
        listOfText.add("Max: I see, but to get out of the forest, first let me teach you about the animals in the forest.");
        listOfText.add("Norah: Alright!");
        //elephant information
        listOfText.add("Max: First is the Elephant, elephants are the world's largest land animal! It can reach 3 meters tall and weight between 3,000 to 7,500kg");
        listOfText.add("Max: Elephants are herbivores which means they eat leaves, twigs, fruits and even roots.");
        listOfText.add("");

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