package com.example.webviewalura;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);

        WebViewClient webViewClient = new WebViewClientImpl(this);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl("https://www.alura.com.br/");

        WebSettings webSettings = webView.getSettings();
        //habitando o JavaScript
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sair do aplicativo");
        builder.setMessage("Deseja sair do aplicativo?");
        builder.setPositiveButton("Sim", (dialog, which) -> {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.mainproject");
            if (intent != null) {
                startActivity(intent);
                finish();
            }
        }).setNegativeButton("Não", null);
        builder.show();
    }
}
