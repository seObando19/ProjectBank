package com.example.projectbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView ident,pass;
    Button btnInit,btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ident=findViewById(R.id.etIdent);
        pass=findViewById(R.id.etPass);
        btnInit=findViewById(R.id.btnIniciar);
        btnReg=findViewById(R.id.btnRegistrar);

        final String TextIdent=ident.getText().toString();
        final String TextPass=pass.getText().toString();

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,inicio.class);
                intent.putExtra("ident",TextIdent);
                intent.putExtra("pass",TextPass);
                startActivity(intent);
                ident.setText("");
                pass.setText("");
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
            }
        });

    }
}
