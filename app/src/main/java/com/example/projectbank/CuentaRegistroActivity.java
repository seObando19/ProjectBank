package com.example.projectbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class CuentaRegistroActivity extends AppCompatActivity {

    EditText nroCuneta,ident,saldo;
    Button addAccount,Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_registro);
        
        nroCuneta=findViewById(R.id.etNrocuenta);
        ident=findViewById(R.id.etIdentificacion);
        saldo=findViewById(R.id.etsaldoadd);
        addAccount=findViewById(R.id.btnNuevaCuenta);
        Cancel=findViewById(R.id.btnCancelar);
        
        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarRegistro();
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarcampos();
            }
        });
    }

    private void validarRegistro() {
        
        final String cuenta=nroCuneta.getText().toString();
        final String identificacion=ident.getText().toString();
        final String sald=saldo.getText().toString();
        
        if (cuenta.isEmpty() || identificacion.isEmpty() || sald.isEmpty())
        {
            Toast.makeText(this,"Campos obligatorios",Toast.LENGTH_LONG).show();
            
        }else
            {
                RegistroUsuario("http://172.16.22.4:8081/ProjectBankSOP/ingresaCuenta.php");
                limpiarcampos();
            }
    }

    private void RegistroUsuario(String s) {
        final String cuenta=nroCuneta.getText().toString();
        final String identificacion=ident.getText().toString();
        final String sald=saldo.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(CuentaRegistroActivity.this, "Registro de usuario exitoso", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                 Toast.makeText(CuentaRegistroActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        })
        {
            //En el siguiente codigo de JSON a la BD
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("nrocuenta",cuenta);
                parametros.put("ident",identificacion);
                parametros.put("saldo",sald);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void limpiarcampos() {
        nroCuneta.setText("");
        ident.setText("");
        saldo.setText("");        
    }
}
