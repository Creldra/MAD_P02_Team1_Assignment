package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

import java.util.ArrayList;


public class StoryPage extends AppCompatActivity {
    private final String TAG = "StoryPage: ";
    Button NextButton;
    Button MenuButton;
    ImageView M_Character;
    ImageView S_Character;

    TextView dialogueText;
    private View decorView;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);
    MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
    private int lineNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);

        NextButton = findViewById(R.id.NextButton);
        MenuButton = findViewById(R.id.Menubutton);
        M_Character = findViewById(R.id.M_Character);
        S_Character = findViewById(R.id.S_Character);
        dialogueText = findViewById(R.id.Dialogue);

        //getting the storyline out
        Log.v(TAG, "Adding Chapter 1 Story to Database");
        ChapterOneStoryline storyline = new ChapterOneStoryline();
        storyline.AddingStory(StoryPage.this);

        //To show the first line of text at the beginning
        final StorySQLite textData = dbHandler.getStory(lineNumber);
        dialogueText.setText(textData.getStoryText());

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineNumber += 1;
                /*
                Tried using the If-Else statement to catch the return of Null but can't seem to work
                So by using Try and Catch in this case is better
                 */
                try {
                    StorySQLite textData = dbHandler.getStory(lineNumber);
                    dialogueText.setText(textData.getStoryText());
                    Log.v(TAG,"getting & setting line" + lineNumber + ": " + textData.getStoryText());
                }catch (Exception e){
                    dialogueText.setText("End of Story");
                    Log.v(TAG, "The Story has ended");
                }
            }
        });

        // Menu Button with alert box
        MenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StoryPage.this);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goMainActivity = new Intent(StoryPage.this, MainActivity.class);
                        startActivity(goMainActivity);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setMessage("Do you  want to go back to menu page?");
                AlertDialog exitAlert = builder.create();
                exitAlert.show();
            }
        });

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
