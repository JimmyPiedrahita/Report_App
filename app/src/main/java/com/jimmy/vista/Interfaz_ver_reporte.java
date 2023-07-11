package com.jimmy.vista;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jimmy.controller.ControllerBaseDatos;
import com.jimmy.modelo.Reportes;
import com.jimmy.reportemayor.R;
public class Interfaz_ver_reporte extends AppCompatActivity implements ControllerBaseDatos.ConsultaCallback {
private EditText txt_codigo;
private TextView lbl_codigo,lbl_sala,lbl_pc,lbl_sede,lbl_descripcion,lbl_estado_reporte;
private ControllerBaseDatos controlBd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_ver_reporte);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Color_principal_dark));
        txt_codigo = (EditText) findViewById(R.id.txt_codigo_ingresado);
        lbl_codigo = (TextView) findViewById(R.id.lbl_codigo_reporte);
        lbl_sala = (TextView) findViewById(R.id.lbl_sala);
        lbl_pc = (TextView) findViewById(R.id.lbl_pc);
        lbl_sede = (TextView) findViewById(R.id.lbl_sede);
        lbl_descripcion = (TextView) findViewById(R.id.lbl_descripcion);
        lbl_estado_reporte = (TextView) findViewById(R.id.lbl_estado_reporte);
    }
    @Override
    public void onConsultaExitosa(Reportes reporte) {
        lbl_codigo.setText(String.valueOf(reporte.getCodigo()));
        lbl_descripcion.setText(reporte.getDescripcion());
        lbl_pc.setText(reporte.getPc());
        lbl_sala.setText(reporte.getSala());
        lbl_sede.setText(reporte.getSede());
        lbl_estado_reporte.setText(reporte.getEstado());
    }
    @Override
    public void onConsultaError(String mensajeError) {
        Toast.makeText(this, mensajeError , Toast.LENGTH_SHORT).show();
    }
    public void mostrarReporte(View view){
        if(txt_codigo.getText().toString().trim().isEmpty())return;
        int codigoEscrito = Integer.parseInt(txt_codigo.getText().toString().trim());
        controlBd = new ControllerBaseDatos();
        controlBd.consultar(codigoEscrito,this,this);
    }
}