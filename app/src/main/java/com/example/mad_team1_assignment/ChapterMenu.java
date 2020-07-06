package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChapterMenu extends AppCompatActivity {
    Button chapterOne;
    Button backButton;
    private View decorView;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);
    public static boolean sActive;
    public static MediaPlayer defaultBgm;
    public MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_menu);

        chapterOne = findViewById(R.id.chapterOneButton);
        chapterOne.setHapticFeedbackEnabled(MainActivity.haptic);

        backButton = findViewById(R.id.backButton);
        backButton.setHapticFeedbackEnabled(MainActivity.haptic);


        buttonSound = MediaPlayer.create(this, R.raw.defaultbutton_sound);

        buttonSound.setVolume(MainActivity.SFXVolume,MainActivity.SFXVolume);

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
                MainActivity.performHaptic(v);
                redirectTo(StoryPage.class);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.performHaptic(v);
                redirectTo(MainActivity.class);
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

        // pausing the player in case of exiting from the app
        if (MainActivity.defaultBgm.isPlaying() && !(MainActivity.sActive || OptionPage.sActive || StoryPage.sActive)) {
            MainActivity.defaultBgm.pause();
        }
        super.onStop();
        finish();
    }

    @Override
    protected void onResume() {
        sActive = true;

        //unpausing the player in case of resuming to others
        if (!MainActivity.defaultBgm.isPlaying()) {
            MainActivity.defaultBgm.start();
            MainActivity.defaultBgm.setLooping(true);
        }

        super.onResume();
    }

    private void redirectTo(Class final_dest){
        Intent intent = new Intent(ChapterMenu.this, final_dest);
        buttonSound.start();
        startActivity(intent);
    }
}
