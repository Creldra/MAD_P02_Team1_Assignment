package com.example.mad_team1_assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {
    Button backButton;
    Button logonButton;
    Button newaccountButton;
    EditText editPassword;
    String password;
    String storedPassword;
    private static final String TAG = "Loginpage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        backButton=findViewById(R.id.backbutton);
        logonButton=findViewById(R.id.loginbutton);
        newaccountButton=findViewById(R.id.newaccountbutton);
        editPassword=findViewById(R.id.editTextTextPassword);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        storedPassword=pref.getString("Password", null); // getting String
        Log.v(TAG,"Stored Password: "+storedPassword);


        final AlertDialog.Builder incorrectPWbuilder = new AlertDialog.Builder(this);

        incorrectPWbuilder.setTitle("Alert!");
        incorrectPWbuilder.setMessage("Incorrect password");
        incorrectPWbuilder.setCancelable(false);
        incorrectPWbuilder.setPositiveButton("close", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){

            }
        });

        final AlertDialog.Builder noAccountbuilder = new AlertDialog.Builder(this);

        noAccountbuilder.setTitle("No Account Data!");
        noAccountbuilder.setMessage("Please create an account");
        noAccountbuilder.setCancelable(false);
        noAccountbuilder.setPositiveButton("close", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goOptions = new Intent(LoginPage.this, OptionPage.class);
                startActivity(goOptions);
            }
        });
        newaccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCreateNew = new Intent(LoginPage.this, NewAccount.class);
                startActivity(goCreateNew);
            }
        });

        logonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                username= editUsername.getText().toString();


                if(storedPassword!=null ){
                    password=editPassword.getText().toString();

                    Log.v(TAG,"button pressed, Password: "+ password );
                    if( password.equals( storedPassword))
                    {
                        Intent goParental = new Intent(LoginPage.this, ParentalPage.class);
                        startActivity(goParental);
                    }
                    else{

                        AlertDialog incorrectalert = incorrectPWbuilder.create();
                        incorrectalert.show();



                    }}
                else
                    {

                        AlertDialog noAccountalert = noAccountbuilder.create();
                        noAccountalert.show();


                    }

            }
        });

    }



}