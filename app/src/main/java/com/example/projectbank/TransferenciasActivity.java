package com.example.projectbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class TransferenciasActivity extends AppCompatActivity {

    EditText cuentOrigen,cuentDestino,valor;
    Button btnAddTransacc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferemcias);
        cuentOrigen = findViewById(R.id.etCuentaOrigen);
        cuentDestino = findViewById(R.id.etCuentaDestino);
        valor = findViewById(R.id.etValor);
        btnAddTransacc = findViewById(R.id.btnTransacc);

        btnAddTransacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDatos();
            }
        });
    }

    private void validarDatos() {

        final String cOrigen = cuentOrigen.getText().toString();
        final String cDestino = cuentDestino.getText().toString();
        final String cvalor = valor.getText().toString();

        if (cOrigen.isEmpty() || cDestino.isEmpty() || cvalor.isEmpty())
        {
            Toast.makeText(this,"Campos obligatorios",Toast.LENGTH_LONG).show();
        }else
            {
                RegistrarUsuario("http://192.168.1.62:81/ProjectBankSOP/ingresaTransaccion.php");
                limpiarDatos();
            }
    }

    private void RegistrarUsuario(String s) {
        final String cOrigen = cuentOrigen.getText().toString();
        final String cDestino = cuentDestino.getText().toString();
        final String cvalor = valor.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(TransferenciasActivity.this, "Transaccion exitoso", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TransferenciasActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            //En el siguiente codigo de JSON a la BD
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nrocuentaorigen", cOrigen);
                parametros.put("nrocuentadestino", cDestino);
                parametros.put("valor", cvalor);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void limpiarDatos() {
        cuentOrigen.setText("");
        cuentDestino.setText("");
        valor.setText("");
    }
}
