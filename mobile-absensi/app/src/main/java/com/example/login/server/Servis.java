package com.example.login.server;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servis {
    private static Retrofit retro;

    public static Retrofit htppclient(){

        if (retro == null) {
            retro = new Retrofit.Builder()
                    .baseUrl("https://23d3-182-1-137-7.ngrok-free.app/example/api")
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
        }
        return retro;
    }

}
