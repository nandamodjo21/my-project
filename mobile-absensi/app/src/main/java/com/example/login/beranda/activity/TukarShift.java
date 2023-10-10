package com.example.login.beranda.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.R;
import com.example.login.server.API_URL;
import com.example.login.server.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

public class TukarShift extends AppCompatActivity {

    private Button submit, back;
    private RequestQueue requestQueue;
    String keterangan;
    private EditText ket;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tukar_shift);

        submit = findViewById(R.id.btn_submit);
        ket = findViewById(R.id.keterangan);
        back = findViewById(R.id.btn_co);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keterangan = ket.getText().toString().trim();
                if (keterangan.equals("")){
                    ket.setError("keterangan harus di isi");
                } else {
                    try {
                        Ajuan();
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private void Ajuan() throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kode_perawat", SharedPrefManager.getInstance(this).getKeyPerawat());
        jsonObject.put("keterangan",keterangan);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API_URL.URL_AJUAN, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("code")==1){
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        finish();
                        Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error not found",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(getApplicationContext());requestQueue.add(jsonObjectRequest);
    }
}