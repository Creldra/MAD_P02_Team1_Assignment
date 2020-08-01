package com.example.mad_team1_assignment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class ParentalPage extends AppCompatActivity {
    int valueone;
    int valuetwo;
    int score;
    Button backButton;
    private View decorView;
    int hsUI = new HideSystemUI().hideSystemUI(decorView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_parental_page);

        //To set the System UI Visibility
        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(hsUI);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    //Log.v(TAG, "Hiding the System UI");
                    decorView.setSystemUiVisibility(hsUI);
                }
            }
        });

        backButton = findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goOptions = new Intent(ParentalPage.this, OptionPage.class);
                startActivity(goOptions);
            }
        });

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        score=pref.getInt("Score",999);





        if (score !=999){

        valueone=score;
        valuetwo=6-score;
        drawPie();
        }

        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Alert!");
            builder.setMessage("No data stored. Stats will be available after each game.");
            builder.setCancelable(false);
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){

                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }


    }
    public void drawPie()
    {
        AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(valueone, Color.parseColor("#FFC5FF8C") , "Number Of Correct Answers: "+valueone))//Data (bean that implements the IPieInfo interface)
                .addData(new SimplePieInfo(valuetwo, Color.parseColor("#FFFFD28C") , "Number Of Wrong Answers: "+ valuetwo)).drawText(true)
                .duration(2000).textSize(40);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
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
