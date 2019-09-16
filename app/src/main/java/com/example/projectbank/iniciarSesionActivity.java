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
    Button btnCuentas,btnTrans,btnInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        user=findViewById(R.id.tvUsuario);
        btnCuentas=findViewById(R.id.btnCuenta);
        btnTrans=findViewById(R.id.btnTransaccion);
        btnInfo=findViewById(R.id.btninfoUsuario);
         final String nombre = getIntent().getStringExtra("user");
         final String ident = getIntent().getStringExtra("id");
         final  String mail = getIntent().getStringExtra("mail");

        user.setText(nombre);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(iniciarSesionActivity.this,InforClienteActivity.class);
                info.putExtra("user",nombre);
                info.putExtra("id",ident);
                info.putExtra("mail",mail);
                startActivity(info);
            }
        });

        btnCuentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cuenta = new Intent(iniciarSesionActivity.this,CuentaRegistroActivity.class);
                cuenta.putExtra("id",ident);
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
