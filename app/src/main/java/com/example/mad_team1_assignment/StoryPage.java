package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class StoryPage extends AppCompatActivity {
    Button NextButton;
    Button MenuButton;
    ImageView M_Character;
    ImageView S_Character;
    private View decorView;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);

        NextButton = findViewById(R.id.NextButton);
        MenuButton = findViewById(R.id.Menubutton);
        M_Character = (ImageView) findViewById(R.id.M_Character);
        S_Character = (ImageView) findViewById(R.id.S_Character);

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

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
