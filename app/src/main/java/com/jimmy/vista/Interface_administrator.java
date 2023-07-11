package com.jimmy.vista;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.jimmy.controller.ControllerAdapter;
import com.jimmy.controller.ControllerBaseDatos;
import com.jimmy.modelo.Reportes;
import com.jimmy.reportemayor.MainActivity;
import com.jimmy.reportemayor.R;
import java.util.ArrayList;
import java.util.List;
public class Interface_administrator extends AppCompatActivity {
    RecyclerView recyclerView;
    ControllerAdapter controlAdapter;
    List<Reportes> reportesList;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_administrador);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Color_principal));
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Interface_administrator.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        ControllerBaseDatos controlBd= new ControllerBaseDatos();
        reportesList = controlBd.consultaReciclerView();
        controlAdapter = new ControllerAdapter(Interface_administrator.this, reportesList);
        recyclerView.setAdapter(controlAdapter);
    }
    private void searchList(String text){
        List<Reportes> dataSearchList = new ArrayList<>();
        for (Reportes reporte : reportesList){
            if (reporte.getSede().toLowerCase().contains(text.toLowerCase())){
                dataSearchList.add(reporte);
            }
        }
        controlAdapter.setSearchlist(dataSearchList);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}