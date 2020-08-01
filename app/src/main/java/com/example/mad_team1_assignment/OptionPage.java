package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionPage extends AppCompatActivity {

    private View decorView;
    private Button backButton;
    private Button parentalButton;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);

        //Creation of objects
        parentalButton = findViewById(R.id.parentalbutton);
        backButton = findViewById(R.id.opt_back_button);
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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                Intent goMainMenu = new Intent(OptionPage.this, MainActivity.class);
                startActivity(goMainMenu);
            }
        });

        parentalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonSound.start();
                Intent goParental = new Intent(OptionPage.this,LoginPage.class);
                startActivity(goParental);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}