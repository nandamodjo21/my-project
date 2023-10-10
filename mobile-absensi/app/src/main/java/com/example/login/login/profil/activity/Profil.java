package com.example.login.login.profil.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;

public class Profil extends AppCompatActivity {
private TextView txt;
private String username;
private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        txt = findViewById(R.id.txtanjay);

//        txt.setText(SharedPrefManager.getInstance(this).getKeyUsername());
//
//       if (username.equals("true")){
//
//        }
//
//
//    }

    }

}