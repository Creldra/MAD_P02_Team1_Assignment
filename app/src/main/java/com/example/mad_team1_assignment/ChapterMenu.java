package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChapterMenu extends AppCompatActivity {
    //Initialize Variables
    Button chapterOne;
    Button backButton;
    private View decorView;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_menu);

        //Creation of objects
        chapterOne = findViewById(R.id.chapterOneButton);
        backButton = findViewById(R.id.backButton);
        final MediaPlayer buttonSound = MediaPlayer.create(this, R.raw.button_click);

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

        chapterOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                Intent chapterOne = new Intent(ChapterMenu.this,StoryPage.class);
                startActivity(chapterOne);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                Intent back = new Intent(ChapterMenu.this,MainActivity.class);
                startActivity(back);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
