package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChapterMenu extends AppCompatActivity {
    Button chapterOne;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_menu);
        chapterOne = findViewById(R.id.chapterOneButton);
        backButton = findViewById(R.id.backButton);

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
                finish();
            }
        });
    }

}
