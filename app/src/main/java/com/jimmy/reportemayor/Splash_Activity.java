package com.jimmy.reportemayor;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        ProgressBar progressBar = findViewById(R.id.barra_progreso_splash);
        //Velocidad en milisegundos
        int velocidad = 20;
        int incrementos = 100;
        int incrementoProgreso = 100 / incrementos;
        TextView progresoText = findViewById(R.id.progreso_text);
        Handler handler = new Handler();
        for (int i = 0; i <= incrementos; i++) {
            final int progreso = i * incrementoProgreso;
            handler.postDelayed(() -> {
                progressBar.setProgress(progreso);
                progresoText.setText(String.valueOf(progreso)+"%");
                if (progreso == 100) {
                    Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, i * velocidad);
        }
    }
}