package com.vip.chapetos.caroldesignv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vip.chapetos.caroldesignv1.Objetos.FirebaseReferencia;

import java.util.ArrayList;
import java.util.List;

public class ClassCategoria extends AppCompatActivity {

    DatabaseReference datareferece;
    Button btnSave;
    EditText editTxtIngresar;
    ListView listViewCat;
    List<Categoria> categorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        categorias = new ArrayList<Categoria>();
        datareferece = FirebaseDatabase.getInstance().getReference("Categorias");

        btnSave = (Button) findViewById(R.id.btnSave);
        editTxtIngresar = (EditText) findViewById(R.id.editTxtIngresar);
        listViewCat = (ListView) findViewById(R.id.listCategorias);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cat = editTxtIngresar.getText().toString();

                if(TextUtils.isEmpty(catId)){
                    //crear
                    String id =datareferece.push().getKey();
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_categoria, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
