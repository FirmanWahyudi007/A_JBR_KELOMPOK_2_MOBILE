package com.example.temanautis.API;

import com.example.temanautis.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("artikel")
    Call<ResponseModel> ardArtikelData();
}
