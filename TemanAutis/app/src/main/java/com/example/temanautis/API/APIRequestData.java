package com.example.temanautis.API;

import com.example.temanautis.Model.Login;
import com.example.temanautis.Model.ResponseModel;
import com.example.temanautis.Model.YayasanModel;
import com.example.temanautis.Model.YayasanResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {
    @GET("artikel")
    Call<ResponseModel> ardArtikelData();

    @GET("yayasan")
    Call<YayasanResponse> ardYayasanData();

    @Headers({"Accept: application/json"})
    //@FormUrlEncoded
    @POST("login")
    Call<Login> loginresponse (
            @Query("email") String email,
            @Query("password") String password
    );
}
