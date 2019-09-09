package com.example.projectbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

public class iniciarSesionActivity extends AppCompatActivity  {

    TextView user,saldo;
    Button btnCuentas,btnTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        user=findViewById(R.id.tvUsuario);
        saldo=findViewById(R.id.tvsaldo);
        btnCuentas=findViewById(R.id.btnCuenta);
        btnTrans=findViewById(R.id.btnTransaccion);

        //  String nombre = getIntent().getStringExtra("user");
        //user.setText(nombre);


        btnCuentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cuenta = new Intent(iniciarSesionActivity.this,CuentasActivity.class);
                startActivity(cuenta);
            }
        });

        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transferencia = new Intent(iniciarSesionActivity.this, TransferenciasActivity.class);
                startActivity(transferencia);
            }
        });

    }

}
