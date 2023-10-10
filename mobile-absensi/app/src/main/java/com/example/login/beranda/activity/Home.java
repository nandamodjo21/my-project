package com.example.login.beranda.activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.login.R;


import com.example.login.beranda.NaviActivity;
import com.example.login.login.activity.Login;
import com.example.login.scan.activity.ScanActivity;
import com.example.login.server.API_URL;
import com.example.login.server.SharedPrefManager;

import net.sourceforge.zbar.ImageScanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Home extends AppCompatActivity {

    private ArrayList<String> data;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    private Button scan,qr_code,logout,ajuan,jadwal;
    private ImageView image;
    private TextView resultText,teks_user,textStatus,jam;
   Dialog dialog;
   RequestQueue requestQueue;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageScanner imageScanner = new ImageScanner();
        setContentView(R.layout.activity_home);



//        data = new ArrayList<>();
        qr_code = findViewById(R.id.btn_code);
        logout = findViewById(R.id.btn_logout);
        ajuan = findViewById(R.id.btn_ajuan);
        jadwal = findViewById(R.id.btn_jadwal);
        textStatus = findViewById(R.id.textStatuss);
        jam = findViewById(R.id.teksjam);



        progressDialog = new ProgressDialog(this);

        scan = findViewById(R.id.bt_scan);
        dialog = new Dialog(this);
        resultText = findViewById(R.id.result);
        teks_user = findViewById(R.id.txt);


        LogoutPopup logoutPopup = new LogoutPopup(this);

        String id = SharedPrefManager.getInstance(this).getKeyPerawat();
        String api = API_URL.URL_STATUS + id;
        String waktu = API_URL.URL_JAM + id;

        getStatus(api);
        getJam(waktu);



        jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Beranda.class));
                finish();
            }
        });
        //btn ajuan
        ajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TukarShift.class));
                finish();
            }
        });

//BUTTON LOGOUT
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               logoutPopup.showLogoutPopup();
            }
        });

        //function camera

        teks_user.setText(SharedPrefManager.getInstance(this).getKeyUsername());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("kode_perawat",SharedPrefManager.getInstance(this).getKeyPerawat());

        scan.setOnClickListener(view -> {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API_URL.URL_SCAN, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        if (response.getInt("code") != 0) {
                            if (ContextCompat.checkSelfPermission(Home.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this, Manifest.permission.CAMERA)) {
                                    try {
                                        startScan();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    ActivityCompat.requestPermissions(Home.this, new String[]{Manifest.permission.CAMERA}, 0);
                                }
                            } else {
                                try {
                                    startScan();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();
                        }
                        } catch(JSONException e){
                            e.printStackTrace();
                        }

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"not found",Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue = Volley.newRequestQueue(getApplicationContext());requestQueue.add(jsonObjectRequest);

        });
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //function qr code
      qr_code.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(),Qrcodes.class));
              finish();
          }
      });



    }

    private void getJam(String waktu) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, waktu, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    if (response.getInt("code")==1){

                        JSONObject js = response.getJSONObject("data");
                        String aa = js.getString("waktu");
                        jam.setText(aa);
                    } else {
                        jam.setText("tidak ada shift");
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue nt = Volley.newRequestQueue(this); nt.add(jsonObjectRequest);
    }

    private void getStatus(String api) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, api, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    if (response.getInt("code")==1){

                        JSONObject js = response.getJSONObject("data");
                        String aa = js.getString("status");
                        textStatus.setText(aa);
                    } else {
                        textStatus.setText("belum absen");
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
      RequestQueue nt = Volley.newRequestQueue(this); nt.add(jsonObjectRequest);
    }


    //    fuction proses scan
    private void startScan()throws JSONException {
        Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
        startActivityForResult(intent, 20);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20) {
            if (resultCode == RESULT_OK && data != null) {
                String code = data.getStringExtra("result");

//                Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject();
                    code.equals(jsonObject.put("kd_absen",code));
                    jsonObject.put("kode_perawat", SharedPrefManager.getInstance(this).getKeyPerawat());
                    Log.d("JSON", jsonObject.toString());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API_URL.URL_ABSEN, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
//                                JSONObject data = response.getJSONObject("data");
//                                String js = data.getString(code);
                                if (response.getInt("code") == 1) {
                                    Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "koneksi gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(jsonObjectRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode ==1) {
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                try {
                    startScan();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Gagal Membuka kamera!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

