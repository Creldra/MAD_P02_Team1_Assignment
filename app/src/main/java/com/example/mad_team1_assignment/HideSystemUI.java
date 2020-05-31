package com.example.mad_team1_assignment;

import android.view.View;

public class HideSystemUI {

    //This hides the System UI that includes the Navigation Bar, Title/Action Bar and System Tray
    public int hideSystemUI(View view){
        return view.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | view.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | view.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | view.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | view.SYSTEM_UI_FLAG_FULLSCREEN
                | view.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}
