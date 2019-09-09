package com.example.projectbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CuentasActivity extends AppCompatActivity {

    Button btncrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuentas);
        btncrearCuenta=findViewById(R.id.btnaddCuenta);

        btncrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CuentasActivity.this,CuentaRegistroActivity.class);
                startActivity(intent);
            }
        });

    }
}
