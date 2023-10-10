package com.example.login.login.response;

import org.json.JSONObject;

public class R_login {

    public String id_user;
    public String kode_perawat;
    public String username;

    public String level;

    public R_login(String id_user, String kode_perawat, String username, String level) {


        this.id_user = id_user;
        this.kode_perawat = kode_perawat;
        this.username = username;
        this.level = level;
    }




}
