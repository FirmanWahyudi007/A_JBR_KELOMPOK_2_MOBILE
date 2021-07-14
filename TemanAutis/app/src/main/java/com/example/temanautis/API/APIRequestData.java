package com.example.temanautis.API;

import com.example.temanautis.Model.DonasiResponse;
import com.example.temanautis.Model.Login;
import com.example.temanautis.Model.Register;
import com.example.temanautis.Model.ResponseModel;
import com.example.temanautis.Model.YayasanResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {
    @GET("artikel")
    Call<ResponseModel> ardArtikelData();

    @GET("yayasan")
    Call<YayasanResponse> ardYayasanData();
    @GET("donasi")
    Call<DonasiResponse> ardDonasiData();

    @Headers({"Accept: application/json"})
    //@FormUrlEncoded
    @POST("login")
    Call<Login> loginresponse (
            @Query("email") String email,
            @Query("password") String password
    );
    @Headers({"Accept: application/json"})
    //@FormUrlEncoded
    @POST("register")
    Call<Register> registerresponse (
            @Query("name") String name,
            @Query("email") String email,
            @Query("password") String password,
            @Query("password_confirmation") String password_confirmation
    );
}
