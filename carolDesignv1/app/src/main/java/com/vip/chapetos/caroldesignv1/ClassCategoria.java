package com.vip.chapetos.caroldesignv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vip.chapetos.caroldesignv1.Objetos.FirebaseReferencia;

public class ClassCategoria extends AppCompatActivity {

    private RecyclerView listaCategoria;
    private CategoriaAdapter adaptador;

    Firebase objReferencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        Firebase.setAndroidContext(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference(FirebaseReferencia.referencia);
        

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        this.listaCategoria = (RecyclerView) findViewById(R.id.listCategorias);
        adaptador = new CategoriaAdapter(this);
        this.listaCategoria.setAdapter(adaptador);
        getDatos();
        //this.listaCategoria.setLayoutManager(LayoutManager);
    }


    public void getDatos(){



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
