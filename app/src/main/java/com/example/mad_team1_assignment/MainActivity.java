package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainMenu: ";
    Button startButton;
    Button optionButton;
    Button exitButton;
    private View decorView;

    public MediaPlayer buttonSound;
    public static boolean haptic = true;
    public static MediaPlayer defaultBgm;
    public static Float BGMVolume = 0.04f;
    public static Float SFXVolume = 0.04f;
    public static boolean sActive;

    int hsUI = new HideSystemUI().hideSystemUI(decorView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        defaultBgm = playsoundtrack(R.raw.default_bgm);

        //Creation of the Objects
        startButton = findViewById(R.id.startbutton);
        startButton.setHapticFeedbackEnabled(haptic);

        optionButton = findViewById(R.id.optionbutton);
        optionButton.setHapticFeedbackEnabled(haptic);

        exitButton = findViewById(R.id.exitbutton);
        exitButton.setHapticFeedbackEnabled(haptic);
        buttonSound = MediaPlayer.create(this, R.raw.defaultbutton_sound);

        buttonSound.setVolume(SFXVolume,SFXVolume);

        //To set the System UI Visibility
        decorView = getWindow().getDecorView();
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
                performHaptic(v);
                redirectTo(ChapterMenu.class);
            }
        });

        //Option Button
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Option Button Clicked");
                performHaptic(v);
                redirectTo(OptionPage.class);

            }
        });

        //Exit Game Button
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.performHaptic(v);
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
        if (defaultBgm.isPlaying()
                && !(OptionPage.sActive || ChapterMenu.sActive)) {
            defaultBgm.pause();
        }
        super.onStop();
        finish();
    }

    @Override
    protected void onResume() {
        // starting the player if it is not playing
        if (!defaultBgm.isPlaying()) {
            defaultBgm.start();
            defaultBgm.setLooping(true);
        }

        // true when activity is active
        sActive = true;

        super.onResume();
    }

    @Override
    protected void onPause() {
        sActive = false;
        super.onPause();
    }

    /*
    On windows focus changed, the System UI visibility will be changed accordingly
    This will only allow the System UI to appear only when the user tap and swipe from the top or right hand side of the screen
    But after awhile, the System UI would automatically hide itself back through the method onWindowFocusChanged()
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            Log.v(TAG, "Changing Window Focus");
            decorView.setSystemUiVisibility(hsUI);
        }
    }

    private void redirectTo(Class final_dest){
        Intent intent = new Intent(MainActivity.this, final_dest);
        buttonSound.start();
        startActivity(intent);
    }

    private MediaPlayer playsoundtrack(int soundtrack){

        MediaPlayer defaultBgm = MediaPlayer.create(this, soundtrack);
        defaultBgm.setVolume(BGMVolume,BGMVolume);
        defaultBgm.setLooping(true);
        defaultBgm.start();


        return defaultBgm;
    }

    public static void performHaptic(View view){
        if (haptic == true){
            view.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
            );
        }
    }

}
