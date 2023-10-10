package com.example.login.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.R;

import com.example.login.beranda.activity.Home;
import com.example.login.server.API_URL;
import com.example.login.server.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    ProgressDialog loading;

    private Button login;
    private RequestQueue requestQueue;


    private String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.login.R.layout.activity_login);

        requestQueue = Volley.newRequestQueue(this);

        EditText l_username = findViewById(R.id.l_username);
        EditText l_password = findViewById(R.id.l_password);
        login = findViewById(R.id.Btn_login);
        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(getApplicationContext(),Home.class));
        }

        //BUTTON LOGIN

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = l_username.getText().toString().trim();
                password = l_password.getText().toString().trim();

                if (username.equals("")){
                    l_username.setError("not user");
                } else if (password.equals("")){
                    l_password.setError("wrong password");
                } else {
                    try {
                        akses_login();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }

                }
        });
}

//FUNCTION LOGIN
private void akses_login() throws JSONException{

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL.URL_LOGIN, jsonObject, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                if (response.getInt("code") == 0){
                    Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject dataObject = response.getJSONObject("data");
                    SharedPrefManager.getInstance(getApplicationContext())
                            .session(dataObject.getString("id_user")
                                    ,dataObject.getString("kode_perawat")
                                    ,dataObject.getString("username"));
                    startActivity(new Intent(getApplicationContext(),Home.class));
                    finish();
                    Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"not found",Toast.LENGTH_SHORT).show();
        }
    });
  requestQueue = Volley.newRequestQueue(getApplicationContext());requestQueue.add(request);
}

}