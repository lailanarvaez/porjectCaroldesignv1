package com.vip.chapetos.caroldesignv1;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class CatList extends ArrayAdapter<Categoria> {

    private Activity context;
    private List<Categoria> categorias;
    DatabaseReference databaseReference;
    EditText edtCat;
    public String id_categ;

    public CatList(@NonNull Activity context, List<Categoria> categorias, DatabaseReference databaseReference, EditText edtCat){
        super(context, R.layout.activity_cellcategoria, categorias);
        this.context = context;
        this.categorias =categorias;
        this.databaseReference = databaseReference;
        this.edtCat = edtCat;
    }

    public View getView(int pos, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_cellcategoria, null, true);

        final TextView txtName = (TextView) listViewItem.findViewById(R.id.txtTitle);
        Button btnDelete = (Button) listViewItem.findViewById(R.id.btnDelete);
        Button btnUpdate = (Button) listViewItem.findViewById(R.id.btnUpdate);

        final Categoria categoria = categorias.get(pos);
        txtName.setText(categoria.getCategoria());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(categoria.getId()).removeValue();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCat.setText(categoria.getCategoria());
                ClassCategoria.catId = categoria.getId();
            }
        });

       txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent classdiseno = new Intent(v.getContext(), ClassDiseno.class);
                classdiseno.putExtra("id_categoria", categoria.getId() );
                classdiseno.putExtra("categoria",txtName.getText().toString());
                v.getContext().startActivity(classdiseno);
                ClassCategoria.catId = categoria.getId();
            }
        });

        return listViewItem;

    }
}
