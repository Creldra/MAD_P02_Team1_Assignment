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
        //Mary - MC, Norah - Fox, Max - Monkey
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
        //Elephant information (Herbivore)
        listOfText.add("Max: Elephants, they are the world's largest land animal! Elephants are known for their large ears, tusks made of ivory and their long trunks.");
        listOfText.add("Max: Elephants are Herbivore which means they eat leaves, twigs, fruits and even roots.");
        //Deer information (Herbivore)
        listOfText.add("Max: Deers have long and powerful legs, a small tail and long ears. Deers are also excellent jumpers and swimmers.");
        listOfText.add("Max: Deers is also Herbivore, they eat grass, tree leaves and fruits.");
        //Jaguar information (Carnivore)
        listOfText.add("Max: Jaguars are the largest of South America’s big cats and the third largest cats in the world.");
        listOfText.add("Max: Jaguars is a Carnivore which means they eat meat and fish.");
        //Tiger information (Carnivore)
        listOfText.add("Max: Tigers are powerful hunter with sharp teeth, strong jaws, and a very agile body.");
        listOfText.add("Max: Tigers is also a Carnivore, they eat meat and fish too.");
        //Fox information (Omnivore)
        listOfText.add("Max: Foxes have narrow faces, long bushy tail and large, triangular ears, fox are known to have excellent hearing.");
        listOfText.add("Max: Foxes are Omnivore, which means they eat meat and plants, but fox only hunt for rabbit and eat berries and fruits.");
        //Bear information (Omnivore)
        listOfText.add("Max: Bears have huge paws, a furry body, a snout and weigh hundreds of kilograms.");
        listOfText.add("Max: Bears is also an Omnivore, they eat meat and plants and some bears also like honey.");

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