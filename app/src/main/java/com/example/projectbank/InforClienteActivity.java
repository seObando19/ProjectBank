package com.example.projectbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InforClienteActivity extends AppCompatActivity {
    private RequestQueue Queue;
    private TextView cedula,nombre,email,saldo;
    private Spinner cuentas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_cliente);

        cedula=findViewById(R.id.resIdent);
        nombre=findViewById(R.id.resNom);
        email=findViewById(R.id.resMail);
        saldo=findViewById(R.id.resSaldo);
        cuentas=findViewById(R.id.spCuentas);

        usuarios Usuario = new usuarios();

        String usuCedula = getIntent().getStringExtra("id");
        String usuNombre = getIntent().getStringExtra("user");
        String usuMail = getIntent().getStringExtra("mail");

        cedula.setText(usuCedula);
        nombre.setText(usuNombre);
        email.setText(usuMail);

        Queue = Volley.newRequestQueue(InforClienteActivity.this);

        cuentasUsu();
    }

    private void cuentasUsu() {
        String id = getIntent().getStringExtra("id");
        String url="http://192.168.1.62:81/ProjectBankSOP/cuentaUserID.php?ident=" + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<String> cuentaUser = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("datos");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject data = jsonArray.getJSONObject(i);
                        String nrocuenta = data.getString("nrocuenta");

                        cuentaUser.add(nrocuenta);

                        ArrayAdapter tipoCuenta = new ArrayAdapter<>(InforClienteActivity.this,android.R.layout.simple_spinner_dropdown_item,cuentaUser);
                        cuentas.setAdapter(tipoCuenta);
                    }
                    // Identificar cuando es presionado en alguno de los elementos.
                    cuentas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                            String tipo = (String)cuentas.getAdapter().getItem(position);
                            saldoUsu();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Queue.add(request);

    }

    private void saldoUsu() {
        String cuenta = (String)cuentas.getSelectedItem();
        String url="http://192.168.1.62:81/ProjectBankSOP/saldoUser.php?nrocuenta=" + cuenta;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("datos");

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        saldo.setText(" ");
                        JSONObject data = jsonArray.getJSONObject(i);
                        String saldoCuenta = data.getString("saldo");

                        //cuentas asociadas delusuario logueado
                        saldo.append(saldoCuenta);
                    }
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Queue.add(request);
    }
}
