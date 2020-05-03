package com.example.ifood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ifood.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirAutentificacao();
            }
        }, 3000);

    }

    private void abrirAutentificacao() {
        Intent intent = new Intent(SplashActivity.this, AutentificacaoActivity.class);
        startActivity(intent);
        finish();
    }
}
