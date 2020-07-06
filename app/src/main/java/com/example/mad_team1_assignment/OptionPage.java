package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import java.util.Arrays;

public class OptionPage extends AppCompatActivity {

    SeekBar sbBGM;
    SeekBar sbSFX;
    Switch switchHaptic;
    private View decorView;
    private Button backButton;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);
    public static MediaPlayer defaultBgm;
    public Float[] volumeArray = { 0.00f, 0.01f, 0.02f, 0.04f, 0.08f };
    static boolean sActive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);

        defaultBgm = MainActivity.defaultBgm;

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

        sbBGM = findViewById(R.id.sbBGM);
        sbBGM.setHapticFeedbackEnabled(MainActivity.haptic);
        sbBGM.setProgress(Arrays.asList(volumeArray).indexOf(MainActivity.BGMVolume) );
        sbBGM.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //change volume if slider moves
                MainActivity.performHaptic(sbBGM);
                changeVolume(defaultBgm, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbSFX = findViewById(R.id.sbSFX);
        sbSFX.setHapticFeedbackEnabled(MainActivity.haptic);
        sbSFX.setProgress(Arrays.asList(volumeArray).indexOf(MainActivity.SFXVolume) );
        sbSFX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //change volume if slider moves
                changeSFXVolume(progress);
                MainActivity.performHaptic(sbSFX);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        switchHaptic = (Switch)findViewById(R.id.switchHaptic);
        switchHaptic.setChecked(MainActivity.haptic);
        switchHaptic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.haptic = isChecked;
                MainActivity.performHaptic(switchHaptic);
            }
        });

        backButton = findViewById(R.id.opt_back_button);
        backButton.setHapticFeedbackEnabled(MainActivity.haptic);
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
        if (MainActivity.defaultBgm.isPlaying() && !(MainActivity.sActive || ChapterMenu.sActive)) {
            MainActivity.defaultBgm.pause();
        }

        super.onStop();
        finish();
    }

    @Override
    protected void onPause() {
        sActive = false;

        super.onPause();
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

    private void changeVolume(MediaPlayer mediaPlayer, int progress){
        float vol = volumeArray[progress];
        mediaPlayer.setVolume(vol, vol);
        MainActivity.BGMVolume = vol;
    }

    private void changeSFXVolume(int progress){
        float vol = volumeArray[progress];
        MainActivity.SFXVolume = vol;
        final MediaPlayer buttonSound = MediaPlayer.create(this, R.raw.defaultbutton_sound);

        buttonSound.setVolume(MainActivity.SFXVolume,MainActivity.SFXVolume);
    }

    private void redirectTo(Class final_dest){
        Intent intent = new Intent(OptionPage.this, final_dest);
        startActivity(intent);
    }


}