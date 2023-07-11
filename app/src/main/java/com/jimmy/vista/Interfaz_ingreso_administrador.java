package com.jimmy.vista;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jimmy.reportemayor.R;
public class Interfaz_ingreso_administrador extends AppCompatActivity {
    private FirebaseFirestore base;
    private EditText txt_clave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Color_secundario));
        setContentView(R.layout.activity_interfaz_ingreso_administrador);

        base = FirebaseFirestore.getInstance();

        txt_clave = (EditText) findViewById(R.id.txt_clave_ingresada);
    }
    public void validarContraseña(View v){
        if(txt_clave.getText().toString().trim().isEmpty())return;
        validarClave(txt_clave.getText().toString());
    }
    public void validarClave(String clave_instroducida){
        base.collection("clave").document("clave_administrador").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String clave = documentSnapshot.getString("CLAVE");
                    if (clave.equals(clave_instroducida)){
                        Toast.makeText(getApplicationContext(), "Clave correcta", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Interfaz_ingreso_administrador.this, Interface_administrator.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "Clave incorrecta", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}