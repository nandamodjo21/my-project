package com.example.login.beranda.interfaces;

import com.example.login.beranda.response.R_scan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface I_scan {

    @POST("/absens")
    Call<R_scan> absen (@Body R_scan r_scan);
}
