package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;

public class StoryPage extends AppCompatActivity {
    Button NextButton;
    Button MenuButton;
    ImageView M_Character;
    ImageView S_Character;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);

        NextButton = findViewById(R.id.NextButton);
        MenuButton = findViewById(R.id.Menubutton);
        M_Character = (ImageView) findViewById(R.id.M_Character);
        S_Character = (ImageView) findViewById(R.id.S_Character);

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        MenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMainActivity = new Intent(StoryPage.this, MainActivity.class);
                startActivity(goMainActivity);
            }
        });


    }


}
