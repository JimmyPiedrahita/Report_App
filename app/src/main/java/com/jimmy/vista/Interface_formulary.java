package com.jimmy.vista;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.jimmy.controller.ControllerBaseDatos;
import com.jimmy.modelo.Reportes;
import com.jimmy.reportemayor.R;
import java.util.Random;
public class Interface_formulary extends AppCompatActivity {
    private ArrayAdapter <String> adapterSalasBi;
    private ArrayAdapter <String> adapterSalasOb;
    private ArrayAdapter <String> adapterSalasEn;
    private ArrayAdapter <String> adapterPcBi1;
    private ArrayAdapter <String> adapterPcBi2;
    private ArrayAdapter <String> adapterPcOb1;
    private ArrayAdapter <String> adapterPcOb2;
    private ArrayAdapter <String> adapterPcEn1;
    private ArrayAdapter <String> adapterPcEn2;
    public Reportes report;
    private EditText txt_nombre_completo,txt_correo_institucional,txt_descripcion_problema;
    private Spinner spinner_sede,spinner_pc,spinner_sala;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_formulario);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Color_principal));

        //LISTA DE SEDES
        String[] sedesUni = {"Bicentenario","Casa Obando","Encarnacion"};

        //LISTA DE SALAS DE CADA SEDE
        String [] salasBi = {"Sala bicentenario 1", "Sala bicentenario 2"};
        String [] salasOb = {"Sala obando 1", "Sala obando 2"};
        String [] salasEn = {"Sala encarnacion 1", "Sala encarnacion 2"};

        //LISTA DE PC DE CADA SALA DE CADA SEDE

        String [] salaBi1_pc_bi = {"Pc_sb1_1","Pc_sb1_2"};
        String [] salaBi2_pc_bi = {"Pc_sb2_1","PC_sb2_2"};
        String [] salaOb1_pc_ob = {"Pc_so1_1","PC_so1_2"};
        String [] salaOb2_pc_ob = {"Pc_so2_1","PC_so2_2"};
        String [] salaEn1_pc_en = {"Pc_se1_1","PC_se1_2"};
        String [] salaEn2_pc_en = {"Pc_se2_1","PC_se2_2"};

        //Campos de texto
        txt_nombre_completo = findViewById(R.id.txt_nombre_completo_);
        txt_correo_institucional = findViewById(R.id.txt_correo_institucional_);
        txt_descripcion_problema = findViewById(R.id.txt_descripcion_problema_);

        //Contenedores de los spinners
        spinner_sede = findViewById(R.id.spinner_sede);
        spinner_sala = findViewById(R.id.spinner_sala);
        spinner_pc = findViewById(R.id.spinner_pc);

        //Adaptadores spinners
        ArrayAdapter<String> adapterSedes = new ArrayAdapter<>(this, R.layout.spinner_item_jimmy, sedesUni);
        adapterSalasBi = new ArrayAdapter<>(this,R.layout.spinner_item_jimmy, salasBi);
        adapterSalasOb = new ArrayAdapter<>(this,R.layout.spinner_item_jimmy, salasOb);
        adapterSalasEn = new ArrayAdapter<>(this,R.layout.spinner_item_jimmy, salasEn);
        adapterPcBi1 = new ArrayAdapter<>(this,R.layout.spinner_item_jimmy, salaBi1_pc_bi);
        adapterPcBi2 = new ArrayAdapter<>(this,R.layout.spinner_item_jimmy, salaBi2_pc_bi);
        adapterPcOb1 = new ArrayAdapter<>(this,R.layout.spinner_item_jimmy, salaOb1_pc_ob);
        adapterPcOb2 = new ArrayAdapter<>(this,R.layout.spinner_item_jimmy, salaOb2_pc_ob);
        adapterPcEn1 = new ArrayAdapter<>(this,R.layout.spinner_item_jimmy, salaEn1_pc_en);
        adapterPcEn2 = new ArrayAdapter<>(this,R.layout.spinner_item_jimmy, salaEn2_pc_en);

        //Valores Default de los spinners
        spinner_sede.setAdapter(adapterSedes);
        spinner_sala.setAdapter(adapterSalasBi);
        spinner_pc.setAdapter(adapterPcBi1);

        //Accion de elementos seleccionados del spinner sede
        spinner_sede.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                escogerSalas(spinner_sede.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //Accion de elementos seleccionados del spinner sala
        spinner_sala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                escogerPc(spinner_sala.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
    public void escogerSalas(String seleccion){
        if(seleccion.equals("Bicentenario")){spinner_sala.setAdapter(adapterSalasBi);return;}
        if(seleccion.equals("Casa Obando")){spinner_sala.setAdapter(adapterSalasOb);return;}
        if(seleccion.equals("Encarnacion")){spinner_sala.setAdapter(adapterSalasEn);}
    }
    public void escogerPc(String seleccion){
        if(seleccion.equals("Sala bicentenario 1")){spinner_pc.setAdapter(adapterPcBi1);return;}
        if(seleccion.equals("Sala bicentenario 2")){spinner_pc.setAdapter(adapterPcBi2);return;}
        if(seleccion.equals("Sala obando 1")){spinner_pc.setAdapter(adapterPcOb1);return;}
        if(seleccion.equals("Sala obando 2")){spinner_pc.setAdapter(adapterPcOb2);return;}
        if(seleccion.equals("Sala encarnacion 1")){spinner_pc.setAdapter(adapterPcEn1);return;}
        if(seleccion.equals("Sala encarnacion 2")){spinner_pc.setAdapter(adapterPcEn2);}
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public boolean validarCampos(){

        if(txt_nombre_completo.getText().toString().trim().equals("")){
            txt_nombre_completo.setBackground(getDrawable(R.drawable.rounded_background_failed));
            return false;
        }else{
            txt_nombre_completo.setBackground(getDrawable(R.drawable.field_text_style));
        }
        if(txt_correo_institucional.getText().toString().trim().equals("")){
            txt_correo_institucional.setBackground(getDrawable(R.drawable.rounded_background_failed));
            return false;
        }else{
            txt_correo_institucional.setBackground(getDrawable(R.drawable.field_text_style));
        }
        if(txt_descripcion_problema.getText().toString().trim().equals("")){
            txt_descripcion_problema.setBackground(getDrawable(R.drawable.field_text_style_descripcion_failed));
            return false;
        }else{
            txt_descripcion_problema.setBackground(getDrawable(R.drawable.field_text_style_descripcion));
        }
        return true;
    }
    private boolean conectadoInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        }
        return false;
    }
    public int generarCodigo() {
        Random random = new Random();
        return random.nextInt(1001) + 1000;
    }
    public void mostrarCodigoReporte(int codigo){
        AlertDialog.Builder builder = new AlertDialog.Builder(Interface_formulary.this);
        builder.setTitle("CODIGO: "+codigo);
        builder.setMessage("Con este codigo podras buscar el reporte para conocer su estado");
        builder.setPositiveButton("Aceptar", (dialog, which) -> {dialog.dismiss();finish();});
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void generarReporte(View view){
        ControllerBaseDatos controlBd = new ControllerBaseDatos();
        if(validarCampos()){
            String n,c,s,sa,p,d;
            n = txt_nombre_completo.getText().toString();
            c = txt_correo_institucional.getText().toString();
            s = spinner_sede.getSelectedItem().toString();
            sa = spinner_sala.getSelectedItem().toString();
            p = spinner_pc.getSelectedItem().toString();
            d = txt_descripcion_problema.getText().toString();
            String estadoDefault = "Sin atender";
            //Crear reporte con los datos
            int codigoAlea = generarCodigo();
            while(controlBd.validarCodigoGenerado(codigoAlea,this)){
                codigoAlea = generarCodigo();
            }
            report = new Reportes(codigoAlea,n,c,s,sa,p,d,estadoDefault);
            // Verificar la conectividad a Internet
            if (conectadoInternet()) {
                controlBd = new ControllerBaseDatos();
                controlBd.guardarReporte(report,this);
            } else {
                Toast.makeText(getApplicationContext(), "No hay conexion a internet", Toast.LENGTH_SHORT).show();
                return;
            }
            mostrarCodigoReporte(codigoAlea);
        }else{
            Toast.makeText(this,"Llene todos los campos",Toast.LENGTH_SHORT).show();
        }
    }
}