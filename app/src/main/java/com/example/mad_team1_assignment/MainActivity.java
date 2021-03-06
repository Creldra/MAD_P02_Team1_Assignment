package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //Initialize Variables
    private final String TAG = "MainMenu: ";
    Button startButton;
    Button optionButton;
    Button exitButton;
    Button achievementButton;
    private View decorView;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creation of the Objects
        startButton = findViewById(R.id.startbutton);
        optionButton = findViewById(R.id.optionbutton);
        exitButton = findViewById(R.id.exitbutton);
        achievementButton = findViewById(R.id.achievementButton);
        final MediaPlayer buttonSound = MediaPlayer.create(this, R.raw.button_click);

        //To set the System UI Visibility
        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(hsUI);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    Log.v(TAG, "Hiding the System UI");
                    decorView.setSystemUiVisibility(hsUI);
                }
            }
        });

        //Start Button transit to ChapterMenu
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Start Button Clicked");
                buttonSound.start();

                Intent goChapterPage = new Intent(MainActivity.this, ChapterMenu.class);
                startActivity(goChapterPage);
            }
        });

        //Option Button
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Option Button Clicked");
                buttonSound.start();
                Intent goOptionPage = new Intent(MainActivity.this, OptionPage.class);
                startActivity(goOptionPage);
            }
        });

        //Achievements Button
        achievementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Achievement Button Clicked");
                buttonSound.start();
                Intent goAchievementPage = new Intent(MainActivity.this, AchievementsPage.class);
                startActivity(goAchievementPage);
            }
        });


        //Exit Game Button
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Exit Button Clicked");
                buttonSound.start();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setMessage("Do you really want to exit the game");
                AlertDialog exitAlert = builder.create();
                exitAlert.show();
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
