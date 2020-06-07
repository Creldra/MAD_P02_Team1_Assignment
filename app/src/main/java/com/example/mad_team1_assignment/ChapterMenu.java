package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChapterMenu extends AppCompatActivity {
    Button chapterOne;
    Button backButton;
    private View decorView;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_menu);

        chapterOne = findViewById(R.id.chapterOneButton);
        backButton = findViewById(R.id.backButton);

        //To set the System UI Visibility
        decorView = getWindow().getDecorView();
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
             Intent chapterOne = new Intent(ChapterMenu.this,StoryPage.class);
             startActivity(chapterOne);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ChapterMenu.this,MainActivity.class);
                startActivity(back);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            decorView.setSystemUiVisibility(hsUI);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
