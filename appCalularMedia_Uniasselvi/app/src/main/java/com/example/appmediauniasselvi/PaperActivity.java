package com.example.appmediauniasselvi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PaperActivity extends AppCompatActivity {

    private EditText avaliacao1;
    private EditText avaliacao2;

    private TextView textMedia;
    private TextView textmediaFinal;
    private TextView textresulatado;

    private Button limpar;
    private Button calcular;

    private double media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);

        avaliacao1 = findViewById(R.id.avaliacaoPaper);
        avaliacao2 = findViewById(R.id.avaliacaoSocializacao);

        textMedia = findViewById(R.id.textMedia);
        textmediaFinal = findViewById(R.id.textMedia_Final);
        textresulatado = findViewById(R.id.textResultado);

        calcular = findViewById(R.id.buttonCalcular);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularPaper();
            }
        });

        limpar = findViewById(R.id.buttonLimpar);
        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                limparCampos();
            }
        });
    }

    public void calcularPaper(){

        DecimalFormat formatar = new DecimalFormat("0.00");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        if ((avaliacao1.getText().toString().isEmpty()) || (avaliacao2.getText().toString().isEmpty())){
            Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
        }else{
            double nota1 = Float.parseFloat(avaliacao1.getText().toString());
            double nota2 = Float.parseFloat(avaliacao2.getText().toString());

            media = ((nota1 * 7) + (nota2 * 3)) / 10;
        }
        if (media <= 6.50) {
            textMedia.setText("Média: " + formatar.format(media));
            textmediaFinal.setText("Média: " + formatar.format(media));
            textresulatado.setText("Reprovado !");
            textresulatado.setTextColor(Color.RED);
        }
        if ((media >= 6.51) && (media <= 7.00)) {
            textMedia.setText("Média: " + formatar.format(media));
            textmediaFinal.setText("Média Final: 7.00");
            textresulatado.setText("Aprovado !");
            textresulatado.setTextColor(Color.BLUE);
        }
        if ((media > 7.00) && (media <= 7.50)) {
            textMedia.setText("Média: " + formatar.format(media));
            textmediaFinal.setText("Média Final: 7.50");
            textresulatado.setText("Aprovado !");
            textresulatado.setTextColor(Color.BLUE);
        }
        if ((media >= 7.51) && (media <= 8.00)) {
            textMedia.setText("Média: " + formatar.format(media));
            textmediaFinal.setText("Média Final: 8.00");
            textresulatado.setText("Aprovado !");
            textresulatado.setTextColor(Color.BLUE);
        }
        if ((media > 8.00) && (media <= 8.50)) {
            textMedia.setText("Média: " + formatar.format(media));
            textmediaFinal.setText("Média Final: 8.50");
            textresulatado.setText("Aprovado !");
            textresulatado.setTextColor(Color.BLUE);
        }
        if ((media >= 8.51) && (media <= 9.00)) {
            textMedia.setText("Média: " + formatar.format(media));
            textmediaFinal.setText("Média Final: 9.00");
            textresulatado.setText("Aprovado !");
            textresulatado.setTextColor(Color.BLUE);
        }
        if ((media > 9.00) && (media <= 9.50)) {
            textMedia.setText("Média: " + formatar.format(media));
            textmediaFinal.setText("Média Final: 9.50");
            textresulatado.setText("Aprovado !");
            textresulatado.setTextColor(Color.BLUE);
        }
        if ((media >= 9.51) && (media <= 10.00)) {
            textMedia.setText("Média: " + formatar.format(media));
            textmediaFinal.setText("Média Final: 10.00");
            textresulatado.setText("Aprovado !");
            textresulatado.setTextColor(Color.BLUE);
        }
    }

    public void limparCampos(){

        avaliacao1.requestFocus();
        avaliacao1.setText("");
        avaliacao2.setText("");

        textMedia.setText("");
        textmediaFinal.setText("");
        textresulatado.setText("");
    }

    @Override
    public  void finish(){
        super.finish();
        overridePendingTransition(R.anim.mover_esquerda,R.anim.fade_out);
    }
}
