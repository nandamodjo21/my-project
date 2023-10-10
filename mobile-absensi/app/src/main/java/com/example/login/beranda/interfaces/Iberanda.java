package com.example.login.beranda.interfaces;

import com.example.login.beranda.response.Rberanda;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Iberanda {
    @GET("notes")
    Call<Rberanda> Ddata();
}