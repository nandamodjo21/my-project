package com.example.login.login.interfaces;


import com.example.login.login.response.R_login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface I_login {


    @POST("/logins")
    Call<R_login> login(@Body R_login r_login);
}

