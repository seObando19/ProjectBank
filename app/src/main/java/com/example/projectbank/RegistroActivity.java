package com.example.projectbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    TextView nomb,email,pass,ident;
    Button btnregistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ident=findViewById(R.id.etIdent);
        nomb=findViewById(R.id.etnom);
        email=findViewById(R.id.etmail);
        pass=findViewById(R.id.etpass);
        btnregistro=findViewById(R.id.btnReg);

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarRegistro();
            }
        });
    }

    private void validarRegistro() {
        final String identE=ident.getText().toString();
        final String nombE=nomb.getText().toString();
        final String emailE=email.getText().toString();
        final String passE=pass.getText().toString();

        if(identE.isEmpty() || nombE.isEmpty() || emailE.isEmpty() || passE.isEmpty())
        {
            Toast.makeText(RegistroActivity.this, "Campos obligatorios", Toast.LENGTH_SHORT).show();
        }else
            {
                //cesde
                //RegistroUsuario("http://172.18.82.90:81/ProjectBankSOP/ingresa.php");
                //casa
                RegistroUsuario("http://192.168.1.72:81/ProjectBankSOP/ingresa.php");
                limpiarcampos();
            }
    }

    private void limpiarcampos() {
        ident.setText("");
        nomb.setText("");
        email.setText("");
        pass.setText("");
    }

    private void RegistroUsuario(String s) {

        final String identE=ident.getText().toString();
        final String nombE=nomb.getText().toString();
        final String emailE=email.getText().toString();
        final String passE=pass.getText().toString();

        //se crea objeto solicitud http
        StringRequest stringRequest = new StringRequest(Request.Method.POST, s, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Toast.makeText(RegistroActivity.this, "Registro de usuario exitoso", Toast.LENGTH_SHORT).show();
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegistroActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        })
        {
            //El siguiente codigo pasa a formato JSON a la BD
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("ident",identE);
                parametros.put("nombres",nombE);
                parametros.put("email",emailE);
                parametros.put("clave",passE);
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
