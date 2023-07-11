package com.jimmy.controller;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.jimmy.modelo.Reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ControllerBaseDatos {
    Task <QuerySnapshot> task;
    QuerySnapshot querySnapshot;
    static List<DocumentSnapshot> listaRep;
    private FirebaseFirestore db;
    Query query;
    public void consultar(int codigo_introducido, Activity mostrarAlerta, ConsultaCallback callback) {
        db = FirebaseFirestore.getInstance();
        db.collection("Reportes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (!task.isSuccessful()) {
                    callback.onConsultaError("Error en el task consultar: "+task.getException());
                    return;
                }
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot == null) {
                    callback.onConsultaError("QuerySnap es null");
                    return;
                }
                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                    Long codigo_buscado = document.getLong("Codigo");
                    if (codigo_buscado == codigo_introducido) {
                        Reportes reporteEncontrado = new Reportes(
                                Integer.parseInt(String.valueOf(document.getLong("Codigo"))),
                                document.getString("Nombre"), document.getString("Correo"),
                                document.getString("Sede"), document.getString("Sala"),
                                document.getString("Pc"), document.getString("Descripcion"),
                                document.getString("Estado"));
                        callback.onConsultaExitosa(reporteEncontrado);
                        return;
                    }
                }
                callback.onConsultaError("No se encontró el reporte");
            }
        });
    }
    public void guardarReporte(Reportes nReporte, Activity mostrarAlerta){
        db = FirebaseFirestore.getInstance();
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("Codigo", nReporte.getCodigo());
        reporte.put("Nombre", nReporte.getNombre());
        reporte.put("Correo", nReporte.getCorreo());
        reporte.put("Sede", nReporte.getSede());
        reporte.put("Sala", nReporte.getSala());
        reporte.put("Pc", nReporte.getPc());
        reporte.put("Descripcion", nReporte.getDescripcion());
        reporte.put("Estado", nReporte.getEstado());
        db.collection("Reportes")
                .add(reporte)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        ControllerGmail controGm = new ControllerGmail();
                        controGm.enviarGmail(nReporte,mostrarAlerta);
                        Toast.makeText(mostrarAlerta, "Enviado correctamente", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mostrarAlerta, "Error en el envio, intente nuevamente", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public boolean validarCodigoGenerado(int codigo_generado, Activity mostrarAlerta) {
        db = FirebaseFirestore.getInstance();
        final AtomicBoolean valido = new AtomicBoolean(false);
        db.collection("Reportes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null) {
                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                            Long codigo_reporte = document.getLong("Codigo");
                            if (codigo_reporte != codigo_generado) {
                                valido.set(true);
                            }
                        }
                    }
                } else {
                    Toast.makeText(mostrarAlerta, "Error de task", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return valido.get();
    }
    public interface ConsultaCallback {
        void onConsultaExitosa(Reportes reporte);
        void onConsultaError(String mensajeError);
    }
    public List<Reportes> consultaReciclerView(){
        List<Reportes> listReport = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        query = db.collection("Reportes");
        task = query.get();
        while (!task.isSuccessful()){}
        querySnapshot = task.getResult();
        listaRep = querySnapshot.getDocuments();
        for (int i = 0; i < listaRep.size(); i++){
            int codigo = Math.toIntExact(listaRep.get(i).getLong("Codigo"));
            Reportes nuevoReporte = new Reportes(
                    codigo,
                    listaRep.get(i).getString("Nombre"),
                    listaRep.get(i).getString("Correo"),
                    listaRep.get(i).getString("Sede"),
                    listaRep.get(i).getString("Sala"),
                    listaRep.get(i).getString("Pc"),
                    listaRep.get(i).getString("Descripcion"),
                    listaRep.get(i).getString("Estado")
                    );
            listReport.add(nuevoReporte);
        }
        return listReport;
    }
    public void actualizarEstado(String estado, String id_reporte, Activity main){
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("Estado", estado);
        db = FirebaseFirestore.getInstance();
        db.collection("Reportes").document(id_reporte).update(mapa).addOnSuccessListener(unused -> {
            Toast.makeText(main, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Log.d("Error",e.getCause().toString());
        });
    }
    public void eliminarReporte(String identificador, Activity main){
        db = FirebaseFirestore.getInstance();
        db.collection("Reportes").document(identificador).delete().addOnSuccessListener(unused -> {
            Toast.makeText(main, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e ->{
            Log.d("Error",e.getCause().toString());
        });
    }
}