package com.vip.chapetos.caroldesignv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class principal extends AppCompatActivity {

    Button btnadmin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btnadmin = (Button) findViewById(R.id.buttonadmin);

        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentactivitylogin = new Intent(principal.this, login.class);
                startActivity(intentactivitylogin);
            }
        });

    }
}
