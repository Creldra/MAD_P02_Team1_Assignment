package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGamePage extends AppCompatActivity {

    private final String TAG = "EndGamePage: ";
    Button btn_EndGameExit;
    TextView txt_Score;
    private View decorView;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);
    int totalScore;
    int prevtotal;
    int attempts;
    int prevattempts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_page);

        //Creation of Objects
        btn_EndGameExit = findViewById(R.id.btn_EndPageExit);
        txt_Score = findViewById(R.id.txt_Score);
        Intent receivingIntent = getIntent();
        int score = receivingIntent.getIntExtra("score", 0);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();

        //recording attempt number
        prevattempts=pref.getInt("Attempts",0);
        attempts= prevattempts+1;
        editor.putInt("Attempts",attempts);
        //pulling previous scores
        prevtotal=pref.getInt("TotalScore",0);
        totalScore= score +prevtotal;
        editor.putInt("TotalScore",totalScore);

        //saving latest score to shared preferences
        editor.putInt("Score",score);
        editor.apply();



        txt_Score.setText("You got " + score + "/6 Correct!");

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

        //Return Button
        btn_EndGameExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGamePage.this, ChapterMenu.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}