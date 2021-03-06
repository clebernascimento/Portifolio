package com.example.mainproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static androidx.core.content.ContextCompat.startActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView mFinaceiro;
    private CardView mAlura;
    private CardView mImc;
    private CardView mUniasselvi;
    private CardView mIfood;
    private CardView mGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        componentes();

    }

    public void componentes() {
        findViewById(R.id.cardView_Finaceiro).setOnClickListener(this);
        findViewById(R.id.cardView_Alura).setOnClickListener(this);
        findViewById(R.id.cardView_Ifood).setOnClickListener(this);
        findViewById(R.id.cardView_IMC).setOnClickListener(this);
        findViewById(R.id.cardView_Uniasselvi).setOnClickListener(this);
        findViewById(R.id.cardView_GE).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardView_Finaceiro:
                Intent iFinaceiro = getPackageManager().getLaunchIntentForPackage("com.cleber.financeiro");
                if (iFinaceiro != null) {
                    startActivity(iFinaceiro);
                    finish();
                }
                break;

            case R.id.cardView_Alura:
                Intent iAlura = getPackageManager().getLaunchIntentForPackage("com.example.webviewalura");
                if (iAlura != null) {
                    startActivity(iAlura);
                    finish();
                }
                break;

            case R.id.cardView_IMC:
                Intent iImc = getPackageManager().getLaunchIntentForPackage("com.example.calculoimc");
                if (iImc != null) {
                    startActivity(iImc);
                    finish();
                }
                break;

            case R.id.cardView_Uniasselvi:
                Intent iUniasselvi = getPackageManager().getLaunchIntentForPackage("com.example.appmediauniasselvi");
                if (iUniasselvi != null) {
                    startActivity(iUniasselvi);
                    finish();
                }
                break;

            case R.id.cardView_Ifood:
                Intent iIfood = getPackageManager().getLaunchIntentForPackage("com.example.ifood");
                if (iIfood != null) {
                    startActivity(iIfood);
                    finish();
                }
                break;

            case R.id.cardView_GE:
                Intent iGloboEsporte = getPackageManager().getLaunchIntentForPackage("com.example.webgloboesporte");
                if (iGloboEsporte != null) {
                    startActivity(iGloboEsporte);
                    finish();
                }
                break;
        }
    }
}
