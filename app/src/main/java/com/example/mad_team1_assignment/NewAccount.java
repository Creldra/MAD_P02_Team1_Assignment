package com.example.mad_team1_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewAccount extends AppCompatActivity {
EditText editPassword;
Button backButton;
Button createButton;
String password;
    private static final String TAG = "NewAccPAge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        editPassword=findViewById(R.id.editTextPassword);
        backButton=findViewById(R.id.buttonback);
        createButton=findViewById(R.id.buttonCreate);

        final Intent goLogin = new Intent(NewAccount.this, LoginPage.class);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(goLogin);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password=editPassword.getText().toString();
                Log.v(TAG," Password: "+password);

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("Password",password);
                editor.apply();
                Log.v(TAG,"updated sharedpreferences");




                startActivity(goLogin);


            }
        });



    }
}