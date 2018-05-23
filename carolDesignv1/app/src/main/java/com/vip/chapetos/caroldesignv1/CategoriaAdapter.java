package com.vip.chapetos.caroldesignv1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaViewHolder> {

    List<Map<String,Object>> datos;
    Context context;
    private LayoutInflater emsamblador = null;


    public CategoriaAdapter(Context context) {
        if(datos==null){
            datos = new ArrayList<>();
        }
        this.datos = datos;
        this.context = context;
        this.emsamblador = LayoutInflater.from(this.context);

    }

    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = emsamblador.inflate(R.layout.activity_cellcategoria, parent, false);
        return new CategoriaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoriaViewHolder holder, int position) {
        Map<String, Object> dato = (Map<String, Object>) this.datos.get(position);
        holder.title.setText(dato.get("titulo").toString());
    }

    @Override
    public int getItemCount() {
        return this.datos.size();
    }


}
