package com.jimmy.reportemayor;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import com.jimmy.controller.ControllerNavegation;
import com.jimmy.vista.Interface_formulary;
import com.jimmy.vista.Interfaz_ingreso_administrador;
import com.jimmy.vista.Interfaz_ver_reporte;
public class MainActivity extends AppCompatActivity {
    ControllerNavegation control = new ControllerNavegation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Color_principal));
    }
    public void goAdministrador(View v){
        control.goInterfaz(MainActivity.this, Interfaz_ingreso_administrador.class);
    }
    public void goGenerarReporte(View v){
        control.goInterfaz(MainActivity.this, Interface_formulary.class);
    }
    public void goVerReporte(View v){
        control.goInterfaz(MainActivity.this, Interfaz_ver_reporte.class);
    }
}