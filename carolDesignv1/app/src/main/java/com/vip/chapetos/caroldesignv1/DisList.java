package com.vip.chapetos.caroldesignv1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DisList extends ArrayAdapter<Diseno> {

    private Activity context;
    private List<Diseno> disenos;
    DatabaseReference databaseReference;
    EditText edtDiseno;
    ImageView imgDiseno;
    TextView txttitulo;


    public DisList(@NonNull Activity context, List<Diseno> disenos, DatabaseReference databaseReference, EditText edtDiseno, String imgDiseno){
        super(context, R.layout.activity_cell_diseno, disenos);
        this.context = context;
        this.disenos = disenos;
        this.databaseReference = databaseReference;
        this.edtDiseno = edtDiseno;

        //ASIGNO LA IMAGEN
        URL imageUrl = null;
        HttpURLConnection conn = null;
        try {

            imageUrl = new URL(imgDiseno);
            conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2; // el factor de escala a minimizar la imagen, siempre es potencia de 2

            Bitmap imagen = BitmapFactory.decodeStream(conn.getInputStream(), new Rect(0, 0, 0, 0), options);
            this.imgDiseno.setImageBitmap(imagen);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al mostrar la imagen", Toast.LENGTH_SHORT).show();
        }

    }

    public View getView(int pos, View view, ViewGroup parent){

       // if(ClassCategoria.catId =
       /* if(CatList.){


        }*/
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_cell_diseno, null, true);
        TextView txtName = (TextView) listViewItem.findViewById(R.id.txtTitleDiseno);
        ImageView img = (ImageView) listViewItem.findViewById(R.id.imgDiseno);
        Button btnDelete = (Button) listViewItem.findViewById(R.id.btnDeleteDiseno);
        Button btnUpdate = (Button) listViewItem.findViewById(R.id.btnUpdateDiseno);

        final Diseno diseno = disenos.get(pos);
        txtName.setText(diseno.getDiseno());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(diseno.getId_diseno()).removeValue();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDiseno.setText(diseno.getDiseno());
                ClassDiseno.disId = diseno.getId_diseno();
            }
        });

        return listViewItem;

    }
}

/*
public class DisList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_diseno);
    }
}*/
