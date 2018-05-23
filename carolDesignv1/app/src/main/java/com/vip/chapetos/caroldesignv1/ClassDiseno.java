package com.vip.chapetos.caroldesignv1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class ClassDiseno extends AppCompatActivity {

    DatabaseReference datareferece;
    Button btnSave;
    EditText editTextDiseno;
    EditText editTextImg;
    ListView listViewDis;
        List<Diseno> disenos;
    public String id_categoria = "";
    public static String disId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseno);

        this.disenos = new ArrayList<Diseno>();
        datareferece = FirebaseDatabase.getInstance().getReference("disenos");
        Bundle parametros =   this.getIntent().getExtras();//obtengo los parametros
        if(parametros != null){
            this.id_categoria = parametros.getString("id_categoria");
        }

        btnSave = (Button) findViewById(R.id.btnSave);
        this.editTextDiseno = (EditText) findViewById(R.id.editTextDiseno);
        this.editTextImg = (EditText) findViewById(R.id.editTextImg);
        listViewDis = (ListView) findViewById(R.id.listDiseno);//-->MODIFICAR

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String design = editTextDiseno.getText().toString();
                String img = editTextImg.getText().toString();

                if (TextUtils.isEmpty(disId)) {
                    //crear
                    String id = datareferece.push().getKey();
                    Diseno diseno = new Diseno(id, design, img, id_categoria);
                    datareferece.child(id).setValue(diseno);

                    Toast.makeText(ClassDiseno.this, "Diseño creado!", Toast.LENGTH_SHORT).show();
                } else {
                    //modificar
                    datareferece.child(disId).child("categoria").setValue(design);
                    Toast.makeText(ClassDiseno.this, "Diseño Modificado!!!", Toast.LENGTH_SHORT).show();
                }
                editTextDiseno.setText(null);
                editTextImg.setText(null);
                disId = "";
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        datareferece.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                disenos.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Diseno diseno = postSnapshot.getValue(Diseno.class);
                    disenos.add(diseno);
                }

                DisList disAdapter = new DisList(ClassDiseno.this, disenos, datareferece, editTextDiseno, editTextImg.toString());
                listViewDis.setAdapter(disAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
