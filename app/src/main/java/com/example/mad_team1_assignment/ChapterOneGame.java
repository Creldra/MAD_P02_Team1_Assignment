package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class ChapterOneGame extends AppCompatActivity {

    private static final int[] opt_IDS = { R.id.opt_1, R.id.opt_2, R.id.opt_3, R.id.opt_4 };
    private static final String[] question1 = { "Which Animal is a Herbivore?", "Deer", "Fox", "Bear", "Jaguar", "Deer" };
    private static final String[] question2 = { "Which Animal do not eat Meat?", "Tiger", "Bear", "Elephant", "Jaguar", "Elephant" };
    private static final String[] question3 = { "Tiger is classified as a ______ .", "Herbivore", "Carnivore", "Omnivore", "Wativore", "Carnivore" };
    private static final String[] question4 = { "Which Animal is an Omnivore?", "Elephant", "Tiger", "Deer", "Bear", "Bear" };
    private static final String[] question5 = { "What is Elephant not Known for?", "Large Ears", "Tusk", "Huge Legs", "Long Trunks", "Huge Legs" };
    private static final String[] question6 = { "Which Animal do not eat Fruits?", "Elephant", "Tiger", "Fox", "Deer", "Tiger" };
    private static final String[][] questionList = {question1, question2, question3, question4, question5, question6};
    private final String TAG = "ChapterOneGame: ";
    private Button selectedOptions;
    Button btn_Pause;
    TextView txt_Question;
    TextView txt_ReturnView;
    private View decorView;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_one_game);

        //Creation of Objects
        btn_Pause = findViewById(R.id.btn_pause);
        txt_Question = findViewById(R.id.questionText);
        txt_ReturnView = findViewById(R.id.txt_returnView);

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

        //Creation of the Quiz Buttons and TextViews
        final int[] qns_setter = {0};
        int opt_getter = 1;

        txt_Question.setText(questionList[0][0]);
        txt_ReturnView.setText("");
        //Create the Buttons using a For Loop
        for(final int optionID : opt_IDS){
            selectedOptions = findViewById(optionID);
            selectedOptions.setText(questionList[qns_setter[0]][opt_getter]);
            //onClick for each Button in opt_IDS
            selectedOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button selectedButton = findViewById(optionID);
                    String checkText = selectedButton.getText().toString();
                    Log.v(TAG,checkText + " has been clicked!");
                    if(checkText == questionList[qns_setter[0]][5]){
                        txt_ReturnView.setTextColor(Color.parseColor("#008000"));
                        txt_ReturnView.setText("Correct");
                        score += 1;
                    }
                    else{
                        txt_ReturnView.setTextColor(Color.parseColor("#FF0000"));
                        txt_ReturnView.setText("Wrong");
                    }

                    for (final int optionIDs : opt_IDS) {
                        selectedOptions = findViewById(optionIDs);
                        selectedOptions.setClickable(false);
                    }

                    if(qns_setter[0] == 5){
                        Intent intent = new Intent(ChapterOneGame.this, EndGamePage.class);
                        intent.putExtra("score", score);
                        startActivity(intent);
                    }
                    else {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txt_ReturnView.setText("");
                                qns_setter[0] += 1;
                                txt_Question.setText(questionList[qns_setter[0]][0]);
                                int opt_getter2 = 1;
                                for (final int optionIDs : opt_IDS) {
                                    selectedOptions = findViewById(optionIDs);
                                    selectedOptions.setClickable(true);
                                    selectedOptions.setText(questionList[qns_setter[0]][opt_getter2]);
                                    opt_getter2 += 1;
                                }
                            }
                        }, 2000);
                    }
                }
            });
            opt_getter += 1;
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