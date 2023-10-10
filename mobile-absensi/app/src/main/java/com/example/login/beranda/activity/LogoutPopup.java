package com.example.login.beranda.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.login.activity.Login;
import com.example.login.server.SharedPrefManager;

public class LogoutPopup {
    private Context mContext;
    private AlertDialog mAlertDialog;

    public LogoutPopup(Context context) {
        mContext = context;
        createPopup();
    }

    private void createPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup, null);
        builder.setView(view);

        Button btnYes = view.findViewById(R.id.btn_yes);
        Button btnNo = view.findViewById(R.id.btn_no);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil tindakan saat user klik Ya
                // Misalnya, menghapus data sesi atau menghentikan layanan
                // Kemudian tutup popup
                Intent intent = new Intent(mContext,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                SharedPrefManager.getInstance(mContext).logout();

                Toast.makeText(mContext, "Anda telah logout", Toast.LENGTH_SHORT).show();
                mAlertDialog.dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil tindakan saat user klik Tidak
                // Kemudian tutup popup
                mAlertDialog.dismiss();
            }
        });

        mAlertDialog = builder.create();
    }

    public void showLogoutPopup() {
        mAlertDialog.show();
    }
}

