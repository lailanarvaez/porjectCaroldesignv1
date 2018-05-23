package com.example.diana.categoriascrud;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public CatList(@NonNull Activity context, List<Categoria> categorias, DatabaseReference databaseReference, EditText edtCat){
        super(context, R.layout.layout_cat_list, categorias);
        this.context = context;
        this.categorias =categorias;
        this.databaseReference = databaseReference;
        this.edtCat = edtCat;
    }

    public View getView(int pos, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_cat_list, null, true);

        TextView txtName = (TextView) listViewItem.findViewById(R.id.txtName);
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
                MainActivity.catId = categoria.getId();
            }
        });

        return listViewItem;

    }
}
