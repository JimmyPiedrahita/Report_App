package com.jimmy.reportemayor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;

public class Splash_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}