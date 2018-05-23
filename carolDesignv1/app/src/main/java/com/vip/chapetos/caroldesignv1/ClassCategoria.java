package com.vip.chapetos.caroldesignv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class ClassCategoria extends AppCompatActivity {

    DatabaseReference datareferece;

    Button btnSave;
    EditText editTxtIngresar;
    ListView listViewCat;
    List<Categoria> categorias;
    public static String catId;
    CatList context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        categorias = new ArrayList<Categoria>();
        datareferece = FirebaseDatabase.getInstance().getReference("categorias");

        btnSave = (Button) findViewById(R.id.btnSave);
        editTxtIngresar = (EditText) findViewById(R.id.editTxtIngresar);
        listViewCat = (ListView) findViewById(R.id.listCategorias);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cat = editTxtIngresar.getText().toString();

                if (TextUtils.isEmpty(catId)) {
                    //crear
                    String id = datareferece.push().getKey();
                    Categoria categoria = new Categoria(id, cat);
                    datareferece.child(id).setValue(categoria);

                    Toast.makeText(ClassCategoria.this, "Categoria creada", Toast.LENGTH_SHORT).show();
                } else {
                    //modificar
                    datareferece.child(catId).child("categoria").setValue(cat);
                    Toast.makeText(ClassCategoria.this, "Categoria Modificada", Toast.LENGTH_SHORT).show();
                }
                editTxtIngresar.setText(null);
                catId = "";
            }
            });
     /*   System.out.println("sdfsfsssdsfsdfsdfsdfd");
     listViewCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                System.out.println("11111111111111");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("222222222222222");
                            Intent classdiseno = new Intent(getApplicationContext(), ClassDiseno.class);
                            startActivity(classdiseno);

                        } catch (Exception e) {
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }
                }).start();
                }
             });*/

    }
        @Override
            protected void onStart() {
                super.onStart();

            datareferece.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        categorias.clear();

                        for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                            Categoria categoria = postSnapshot.getValue(Categoria.class);
                            categorias.add(categoria);
                        }

                        CatList catAdapter = new CatList(ClassCategoria.this, categorias, datareferece, editTxtIngresar);
                        listViewCat.setAdapter(catAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }