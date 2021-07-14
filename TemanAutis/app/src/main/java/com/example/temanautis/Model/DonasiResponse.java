package com.example.temanautis.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DonasiResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private List<DonasiModel> data;

    @SerializedName("message")
    private String message;

    public void setCode(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public void setData(List<DonasiModel> data){
        this.data = data;
    }

    public List<DonasiModel> getData(){
        return data;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
