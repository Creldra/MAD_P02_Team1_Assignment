package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.mad_team1_assignment.R.layout.activity_achievements_page;

public class AchievementsPage extends AppCompatActivity {
    private View decorView;
    private Button backButton;
    ListView awardList;
    ArrayAdapter ad;

    List<String> awards = new ArrayList<String>();
    String [] startingList ={"First Chapter completed"};


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


        backButton = findViewById(R.id.achievement_backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMainMenu = new Intent(AchievementsPage.this, MainActivity.class);
                startActivity(goMainMenu);
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}