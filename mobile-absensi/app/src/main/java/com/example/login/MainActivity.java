package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.login.login.activity.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sessionManager = new SessionManager(this);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), Login.class));

//                Toast.makeText(getApplicationContext(), "Hai Manteman", Toast.LENGTH_SHORT).show();
            }
        },2500);
    }
}
