package com.jimmy.vista;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jimmy.controller.ControllerBaseDatos;
import com.jimmy.controller.ControllerNavegation;
import com.jimmy.reportemayor.R;

import org.checkerframework.checker.units.qual.C;

public class DetailActivity extends AppCompatActivity {
    TextView lb_nombre, lb_email, lb_sede, lb_sala, lb_pc, lb_codigo, lb_estado,lb_descripcion;
    CheckBox solucionado, en_proceso;
    String identificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Color_principal));
        lb_nombre = findViewById(R.id.lbl_details_nombre);
        lb_email = findViewById(R.id.lbl_details_email);
        lb_sede = findViewById(R.id.lbl_details_sede);
        lb_sala = findViewById(R.id.lbl_details_sala);
        lb_pc = findViewById(R.id.lbl_details_pc);
        lb_codigo = findViewById(R.id.lbl_details_codigo);
        lb_estado = findViewById(R.id.lbl_details_estado);
        lb_descripcion = findViewById(R.id.lbl_details_descripcion);
        solucionado = findViewById(R.id.check_solucionado);
        en_proceso = findViewById(R.id.check_proceso);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            lb_nombre.setText(bundle.getString("Nombre"));
            lb_email.setText(bundle.getString("Email"));
            lb_sede.setText(bundle.getString("Sede"));
            lb_sala.setText(bundle.getString("Sala"));
            lb_pc.setText(bundle.getString("Pc"));
            lb_codigo.setText(bundle.getString("Codigo"));
            lb_estado.setText(bundle.getString("Estado"));
            lb_descripcion.setText(bundle.getString("Descripcion"));
            identificador = bundle.getString("Identificador");
        }
    }
    public boolean validar(){
        if (solucionado.isChecked() && en_proceso.isChecked() || !solucionado.isChecked() && !en_proceso.isChecked()){
            Toast.makeText(this, "Elija una opcion", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void actualizar(View view){
        String estado = "Sin atender";
        if (!validar()){
            return;
        }
        if (solucionado.isChecked()){
            estado = "Solucionado";
        }
        if (en_proceso.isChecked()){
            estado = "En proceso";
        }
        ControllerBaseDatos baseDatos = new ControllerBaseDatos();
        baseDatos.actualizarEstado(estado,identificador,this);
        startActivity(new Intent(this, Interface_administrator.class));
        finish();
    }

    public void delete(View view){
        ControllerBaseDatos control = new ControllerBaseDatos();
        control.eliminarReporte(identificador, this);
        startActivity(new Intent(this, Interface_administrator.class));
        finish();
    }
}