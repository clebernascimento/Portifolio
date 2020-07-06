package com.example.calculoimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        Intent iMainProject = getPackageManager().getLaunchIntentForPackage("com.example.mainproject");
        if (iMainProject != null) {
            startActivity(iMainProject);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        }
        super.onBackPressed();  // optional depending on your needs
    }
}
