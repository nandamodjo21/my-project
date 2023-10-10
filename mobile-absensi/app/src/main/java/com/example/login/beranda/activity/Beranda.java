package com.example.login.beranda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.R;

import com.example.login.beranda.adapter.Adapter;
//import com.example.login.beranda.adapter.Ahome;
import com.example.login.server.API_URL;
import com.example.login.server.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Beranda extends AppCompatActivity {

    private ImageButton backTo;
    private RecyclerView rvData;
    private List<HashMap<String, String>> lisdData;
    private SwipeRefreshLayout srlData;
    List<JSONObject> mj;
    private ProgressBar pbData;
    private RequestQueue requestQueue;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    List<String> list;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        backTo = findViewById(R.id.backHome);
        recyclerView = findViewById(R.id.rv_data);
        srlData = findViewById(R.id.swl_data);
        pbData = findViewById(R.id.pb_data);

        String id = SharedPrefManager.getInstance(this).getKeyPerawat();
        String api = API_URL.URL_JADWAL + id;

        retrieveData(api);
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData(api);
                srlData.setRefreshing(false);
            }
        });

        backTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });

    }



    private void retrieveData(String api){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                list = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    for (int i =0; i < jsonArray.length(); i++){
//                        JSONObject js = jsonArray.getJSONObject(i);
//                        list.add(js.getString("nira"));
//                        list.add(js.getString("nama"));
//                        list.add(js.getString("shift"));
//                        list.add(js.getString("tanggal"));
//                        list.add(js.getString("status"));
//                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject js = jsonArray.getJSONObject(i);
                        String data =  js.getString("nama") + "," + js.getString("shift") + "," + js.getString("tanggal") + "," + js.getString("status");
                        list.add(data);
                    }


                    linearLayoutManager = new LinearLayoutManager(Beranda.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter = new Adapter(Beranda.this,list);
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
