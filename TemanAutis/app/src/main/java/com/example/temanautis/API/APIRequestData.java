package com.example.temanautis.API;

import com.example.temanautis.Model.AcaraResponse;
import com.example.temanautis.Model.DonasiResponse;
import com.example.temanautis.Model.DonateModel;
import com.example.temanautis.Model.DonateResponse;
import com.example.temanautis.Model.ListDonateResponse;
import com.example.temanautis.Model.Login;
import com.example.temanautis.Model.Register;
import com.example.temanautis.Model.ResponseModel;
import com.example.temanautis.Model.VideoResponse;
import com.example.temanautis.Model.YayasanModel;
import com.example.temanautis.Model.YayasanResponse;
import com.example.temanautis.Yayasan;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIRequestData {
    @GET("artikel")
    Call<ResponseModel> ardArtikelData();

    @GET("yayasan")
    Call<YayasanResponse> ardYayasanData();

    @GET("donasi")
    Call<DonasiResponse> ardDonasiData();

    @GET("acara")
    Call<AcaraResponse> ardAcaraData();

    @GET("video")
    Call<VideoResponse> ardVideoData();

    @GET("listdonate")
    Call<ListDonateResponse> ardListDonateData(@Header("Authorization") String token);

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

    @Headers({"Accept: application/json"})
    @GET("cari")
    Call<ResponseModel> search(
            @Query("query") String query
    );
    @Headers({"Accept: application/json"})
    @GET("cari/yayasan")
    Call<YayasanResponse> searchYayasan(
            @Query("query") String query
    );
    @POST("donate")
    Call<DonateResponse> Donate(
            @Header("Authorization") String token,
            @Query("donasi") int donasi,
            @Query("nominal") int nominal,
            @Query("metode") String metode,
            @Query("keterangan") String keterangan);
    
}
