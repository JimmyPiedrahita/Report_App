package com.jimmy.reportemayor;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Splash_Activity extends AppCompatActivity {
    private FirebaseFirestore base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        ProgressBar progressBar = findViewById(R.id.barra_progreso_splash);

        base = FirebaseFirestore.getInstance();
        base.collection("clave").document("velocidad_carga").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //Velocidad en milisegundos
                int velocidad = 0;
                if (documentSnapshot.exists()){
                    velocidad = Integer.parseInt(documentSnapshot.getString("velocidad"));
                    Log.d("VE", String.valueOf(velocidad));
                }
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
        });
    }
}