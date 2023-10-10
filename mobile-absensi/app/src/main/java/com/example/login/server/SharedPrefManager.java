package com.example.login.server;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static SharedPrefManager instance;
    private static Context mCtx;

    private  static final String SHARED_PREF_NAME = "KelasA";
    private static final  String KEY_ID = "id_user";
    private static final String KEY_PERAWAT = "kode_perawat";
    private static final  String KEY_USERNAME = "username";
    private static final  String KEY_LEVEL = "level";
    private static final String KEY_ABSEN = "kd_absen";
    String channelnotif = "channelA" ;
    String channelid = "default" ;


    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public boolean jadwal(String kd_absen){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ABSEN, kd_absen);
        editor.apply();
        return true;
    }
    public String getKeyAbsen(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ABSEN,null);
    }

    public  boolean session(String id_user, String kode_perawat, String username){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, id_user);
        editor.putString(KEY_PERAWAT, kode_perawat);
        editor.putString(KEY_USERNAME, username);
        editor.apply();
        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_ID, "0") != "0"){
            return true;
        }
        return false;
    }

    public  boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public String getKeyId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ID, null);
    }
    public String getKeyUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }
    public String getKeyLevel(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LEVEL, null);
    }
    public String getKeyPerawat(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PERAWAT,null);
    }
}
