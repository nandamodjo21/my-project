package com.example.login.beranda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.R;
import com.example.login.beranda.adapter.Adapter;
import com.example.login.server.API_URL;
import com.example.login.server.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Jadwal extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        recyclerView = findViewById(R.id.rvData);

//        String kode = ;



        getData();

    }

    private void getData(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String api_url = API_URL.URL_JADWAL + SharedPrefManager.getInstance(this).getKeyPerawat();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,api_url , new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                list = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i =0; i < jsonArray.length(); i++){
                        JSONObject js = jsonArray.getJSONObject(i);
                        list.add(js.getString("nira"));
                        list.add(js.getString("nama"));
                        list.add(js.getString("shift"));
                        list.add(js.getString("tanggal"));
                        list.add(js.getString("status"));
                    }

                    linearLayoutManager = new LinearLayoutManager(Jadwal.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter = new Adapter(Jadwal.this,list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error kawan",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }

}