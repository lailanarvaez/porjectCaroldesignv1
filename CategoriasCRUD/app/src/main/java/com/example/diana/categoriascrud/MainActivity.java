package com.example.diana.categoriascrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnSave;
    EditText edtCat;
    DatabaseReference databaseReference;
    ListView listViewCat;
    List<Categoria> categorias;
    public static String catId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorias = new ArrayList<Categoria>();
        databaseReference = FirebaseDatabase.getInstance().getReference("categorias");

        btnSave = (Button) findViewById(R.id.btnSave);
        edtCat = (EditText) findViewById(R.id.edtCat);
        listViewCat = (ListView) findViewById(R.id.ListViewCat);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cat = edtCat.getText().toString();

                if(TextUtils.isEmpty(catId)){
                    //crear
                    String id =databaseReference.push().getKey();
                    Categoria categoria = new Categoria(id, cat);
                    databaseReference.child(id).setValue(categoria);

                    Toast.makeText(MainActivity.this, "Categoria creada", Toast.LENGTH_SHORT).show();
                } else {
                    //modificar
                    databaseReference.child(catId).child("categoria").setValue(cat);
                    Toast.makeText(MainActivity.this, "Categoria Modificada", Toast.LENGTH_SHORT).show();


                }

                edtCat.setText(null);
                catId = "";
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categorias.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Categoria categoria = postSnapshot.getValue(Categoria.class);
                    categorias.add(categoria);
                }

                CatList catAdapter = new CatList(MainActivity.this, categorias, databaseReference, edtCat);
                listViewCat.setAdapter(catAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

