package com.example.projectbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    TextView ident,pass;
    Button btnInit,btnReg;
    //objetos para la conexión.
    private RequestQueue rq;
    private JsonRequest jrq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ident=findViewById(R.id.etIdent);
        pass=findViewById(R.id.etPass);
        btnInit=findViewById(R.id.btnIniciar);
        btnReg=findViewById(R.id.btnRegistrar);

        rq=Volley.newRequestQueue(MainActivity.this);

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

    }

    private void iniciarSesion() {

        String TextIdent=ident.getText().toString();
        String TextPass=pass.getText().toString();
        //cesde
        //String url="http://172.18.82.90:81/ProjectBankSOP/sesion.php?ident=" + TextIdent + "&clave=" +TextPass;
        //casa
        String url="http://192.168.1.72:81/ProjectBankSOP/sesion.php?ident=" + TextIdent + "&clave=" +TextPass;

        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);
    }

    @Override
    public void onResponse(JSONObject response) {
        String TextIdent=ident.getText().toString();
        Toast.makeText(this, "Se ha encontrado el usuario con el identificacion " + TextIdent, Toast.LENGTH_SHORT).show();
        saveInfUser(response);
        limpiarCampos();
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        String TextIdent=ident.getText().toString();
        Toast.makeText(this, "No se ha encontrado el usuario con el identificacion " + TextIdent, Toast.LENGTH_SHORT).show();
    }
    private void limpiarCampos() {
        ident.setText("");
        pass.setText("");
    }
    private void saveInfUser(JSONObject response) {
        //Instanciar la clase usuarios para obtener inf usuario
        usuarios Usuario = new usuarios();

        //Array for send the dates Format JSON
        JSONArray jsonArray = response.optJSONArray("datos");

        JSONObject jsonObject= null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            Usuario.setIdent(jsonObject.optString("ident"));
            Usuario.setNombres(jsonObject.optString("nombres"));
            Usuario.setEmail(jsonObject.optString("email"));
            Usuario.setClave(jsonObject.optString("clave"));
        }catch (JSONException e)
        {
                e.printStackTrace();
        }
        Intent intent = new Intent(MainActivity.this,iniciarSesionActivity.class);
        startActivity(intent);
        finish();
    }

}
