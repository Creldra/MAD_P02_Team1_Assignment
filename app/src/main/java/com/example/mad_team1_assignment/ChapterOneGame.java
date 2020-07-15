package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ChapterOneGame extends AppCompatActivity {

    private final String TAG = "ChapterOneGame: ";
    private static final int[] opt_IDS = { R.id.opt_1, R.id.opt_2, R.id.opt_3, R.id.opt_4 };
    private static final String[] question1 = { "" };
    private static final String[] question2 = { "" };
    private static final String[] question3 = { "" };
    private static final String[] question4 = { "" };
    private static final String[] question5 = { "" };
    private static final String[] question6 = { "" };
    private Button options;
    Button btn_Pause;
    TextView txt_Question;
    private View decorView;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_one_game);

        //Creation of Objects
        btn_Pause = findViewById(R.id.btn_pause);
        txt_Question = findViewById(R.id.questionText);

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

        //TODO: create the first text of questions and options
        Button temp_opt1 = findViewById(opt_IDS[0]);
        temp_opt1.setText(question1[0]);

        for(final int optionID : opt_IDS){
            options = findViewById(optionID);
            options.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: create a loop when clicked to change the text in the buttons
                }
            });
        }

        //Pause Button
        btn_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChapterOneGame.this);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ChapterOneGame.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setMessage("Resume?");
                AlertDialog exitAlert = builder.create();
                exitAlert.show();
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