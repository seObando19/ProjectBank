package com.example.projectbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

public class ActivityRegistro extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
//implements Response.Listener<JSONObject>,Response.ErrorListener
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
