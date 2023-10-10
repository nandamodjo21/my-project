package com.example.login.server;

import android.annotation.SuppressLint;
import android.content.Context;

public class API_URL {
@SuppressLint("StaticFieldLeak")
public static Context mct;
  public static final String kode_perawat = SharedPrefManager.getInstance(null).getKeyPerawat();
public static final String ROOT_URL = "http://7ca7-182-1-150-75.ngrok-free.app";
public static final String URL_LOGIN = ROOT_URL + "/logins";
public static final String URL_ABSEN = ROOT_URL + "/masuk";
public static final String URL_PULANG = ROOT_URL + "/absenpulang";
public static final String URL_SCAN = ROOT_URL + "/cekit";
public static final String URL_QR = ROOT_URL + "/qrcods";
public static final String URL_AJUAN = ROOT_URL + "/tukar";

public static final String URL_JADWAL = ROOT_URL + "/jadwal/";
public static final String URL_STATUS = ROOT_URL + "/status/";
  public static final String URL_JAM = ROOT_URL + "/status/jam/";


}
