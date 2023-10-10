package com.example.login.beranda.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Qrcodes extends AppCompatActivity {

    private Button generate;
    private ImageView scnn,exit;
    private String username,kd_absen,data;

    RequestQueue requestQueue;
    MultiFormatWriter multi = new MultiFormatWriter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodes);

        generate = findViewById(R.id.btn_scann);
        scnn = findViewById(R.id.s__image);
        exit = findViewById(R.id.btn_exit);


//        BUTTON BACK TO HOME
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Home.class));
                finish();
            }
        });


        //BUTTON GENERATE QR CODE
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startQr();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //FUNCTION QR CODE
            private void startQr() throws JSONException {
                JSONObject jsonObject = new JSONObject();
                    jsonObject.put("kode_perawat",SharedPrefManager.getInstance(getApplicationContext()).getKeyPerawat());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API_URL.URL_PULANG, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getInt("code") == 0) {
                                    Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();
                                }  else {
                                    try {
                                        String data = response.getString("data");
                                        BitMatrix bitmap = multi.encode(data, BarcodeFormat.QR_CODE, 500, 500);
                                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                        Bitmap b = barcodeEncoder.createBitmap(bitmap);
                                        scnn.setImageBitmap(b);
                                        Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();

                                    }  catch (WriterException e) {
                                        e.printStackTrace();
                                    }
                                }
                        } catch (JSONException e){
                            e.printStackTrace();
                            finish();
                        }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getApplicationContext(),"not responding",Toast.LENGTH_SHORT).show();
                        }
                    });
                requestQueue = Volley.newRequestQueue(getApplicationContext());requestQueue.add(jsonObjectRequest);
            }
        });
    }
}