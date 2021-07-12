package com.example.temanautis.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
<<<<<<< Updated upstream
    private static final String baseurl = "http://192.168.45.184:8000/api/";
=======
    private static final String baseurl = "http://10.212.17.220:8000/api/";
>>>>>>> Stashed changes
    private static Retrofit retro;

    public static Retrofit konekRetrofit(){
        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
