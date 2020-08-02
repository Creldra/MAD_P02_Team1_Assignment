package com.example.mad_team1_assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.mad_team1_assignment.R.layout.activity_achievements_page;

public class AchievementsPage extends AppCompatActivity {
    private View decorView;
    private Button backButton;
    private Button ClearButton;
    ListView awardList;
    ArrayAdapter ad;
    static List<String> awards = new ArrayList<>();
    static String [] startingList ={};

    int hsUI = new HideSystemUI().hideSystemUI(decorView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_achievements_page);

        //To set the System UI Visibility
        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(hsUI);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    decorView.setSystemUiVisibility(hsUI);
                }
            }
        });

        awardList = findViewById(R.id.awardList);
        awards = new ArrayList<String>(Arrays.asList(startingList));
        ad = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,awards);
        awardList.setAdapter(ad);

        //Retreiving data from Shared Preferences
        SharedPreferences pref = getApplicationContext().getSharedPreferences("AwardsPref", 0);
        awards.add(pref.getString("Achievement1","locked"));
        awards.add(pref.getString("Achievement2","locked"));

        backButton = findViewById(R.id.achievement_backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMainMenu = new Intent(AchievementsPage.this, MainActivity.class);
                startActivity(goMainMenu);
            }
        });

        //to clear the achivements done by the user
        ClearButton = findViewById(R.id.ClearButton);
        ClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("AwardsPref", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                awards.clear();
                ad.notifyDataSetChanged();
                Toast.makeText(AchievementsPage.this,"Cleared all achievements",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}