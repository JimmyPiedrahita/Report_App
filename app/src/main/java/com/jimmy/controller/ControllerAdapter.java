package com.jimmy.controller;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.jimmy.modelo.Reportes;
import com.jimmy.vista.DetailActivity;
import com.jimmy.reportemayor.R;

import java.util.List;
public class ControllerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<Reportes> dataList;
    String identificador;
    public void setSearchlist(List<Reportes> dataSearchList){
        this.dataList = dataSearchList;
        notifyDataSetChanged();
    }
    public ControllerAdapter(Context context, List<Reportes> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_reporte_single, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.sede.setText(dataList.get(position).getSede());
        holder.codigo.setText(String.valueOf(dataList.get(position).getCodigo()));
        holder.estado.setText(dataList.get(position).getEstado());
        holder.cardReporte.setOnClickListener(v -> {
            Intent i = new Intent(context, DetailActivity.class);
            ControllerBaseDatos control = new ControllerBaseDatos();
            identificador = control.listaRep.get(holder.getAdapterPosition()).getId();
            i.putExtra("Nombre",dataList.get(holder.getAdapterPosition()).getNombre());
            i.putExtra("Email",dataList.get(holder.getAdapterPosition()).getCorreo());
            i.putExtra("Sede",dataList.get(holder.getAdapterPosition()).getSede());
            i.putExtra("Sala",dataList.get(holder.getAdapterPosition()).getSala());
            i.putExtra("Pc",dataList.get(holder.getAdapterPosition()).getPc());
            i.putExtra("Codigo",String.valueOf(dataList.get(holder.getAdapterPosition()).getCodigo()));
            i.putExtra("Estado",dataList.get(holder.getAdapterPosition()).getEstado());
            i.putExtra("Descripcion",dataList.get(holder.getAdapterPosition()).getDescripcion());
            i.putExtra("Identificador", identificador);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView sede,codigo,sala,pc,estado;
    CardView cardReporte;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        sede = itemView.findViewById(R.id.txt_sede_card);
        codigo = itemView.findViewById(R.id.txt_codigo_card);
        estado = itemView.findViewById(R.id.txt_estado_card);
        cardReporte = itemView.findViewById(R.id.card_reportes);
    }
}