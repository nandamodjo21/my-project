package com.example.login.scan.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.beranda.response.R_scan;
import com.example.login.server.API_URL;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;


public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private ProgressDialog progressDialog;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        mScannerView.setAspectTolerance(0.5f);
        setContentView(mScannerView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
    @Override
    public void handleResult(Result rawResult) {
                Intent intent = new Intent();
                intent.putExtra("result",rawResult.getText());
                setResult(RESULT_OK, intent);
                finish();
                //KODE YANG DIKIRIM KE ACTIVITY LAIN
    }

}

//    JSONObject data = response.getJSONObject("data");
//    BitMatrix bitmap = multi.encode(String.valueOf(data), BarcodeFormat.QR_CODE, 500, 500);
//    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//    Bitmap b = barcodeEncoder.createBitmap(bitmap);
//                                    scnn.setImageBitmap(b);