package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;


public class StoryPage extends AppCompatActivity {
    //Initialize Variables
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
        M_Character = findViewById(R.id.m_Character);
        S_Character = findViewById(R.id.s_Character);
        dialogueText = findViewById(R.id.Dialogue);
        final MediaPlayer buttonSound = MediaPlayer.create(this, R.raw.button_click);

        //Checks Database Exist
        if(checkDataBase() == false){
            Log.v(TAG, "Adding Chapter 1 Story to Database");
            ChapterOneStoryline storyline = new ChapterOneStoryline();
            storyline.AddingStory(StoryPage.this);
        }
        else if(checkDataBase() == true){
            Log.v(TAG, "Chapter 1 Story Database Already Exist!");
        }

        //To show the first line of text at the beginning
        final StorySQLite textData = dbHandler.getStory(lineNumber);
        dialogueText.setText(textData.getStoryText());

        //Next Button
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
                    changeCharacter(lineNumber);
                    Log.v(TAG,"getting & setting line" + lineNumber + ": " + textData.getStoryText());
                }catch (Exception e){
                    Log.v(TAG, "The Story has ended");
                    Log.v(TAG, "Moving to Game Stage");
                    Intent intent = new Intent(StoryPage.this, ChapterOneGame.class);
                    startActivity(intent);
                }
            }
        });

        // Menu Button with alert box
        MenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
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
        decorView.setSystemUiVisibility(hsUI);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    decorView.setSystemUiVisibility(hsUI);
                }
            }
        });
    }

    //Checks Database Exist Method
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase("/data/data/mad.team1.assignment/databases/story.db",
                                                null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
            return false;
        }
        return checkDB != null;
    }

    //Change Character Image Method
    private void changeCharacter(int lineNumber){
        if(lineNumber == 2){
            S_Character.setImageResource(R.drawable.fox);
        }
        else if (lineNumber == 5){
            S_Character.setImageResource(R.drawable.monkey);
        }
        else if (lineNumber == 12){
            S_Character.setImageResource(R.drawable.elephant);
        }
        else if (lineNumber == 14){
            S_Character.setImageResource(R.drawable.deer);
        }
        else if (lineNumber == 16){
            S_Character.setImageResource(R.drawable.jaguar);
        }
        else if (lineNumber == 18){
            S_Character.setImageResource(R.drawable.tiger);
        }
        else if (lineNumber == 20){
            S_Character.setImageResource(R.drawable.fox);
        }
        else if (lineNumber == 22){
            S_Character.setImageResource(R.drawable.bear);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
