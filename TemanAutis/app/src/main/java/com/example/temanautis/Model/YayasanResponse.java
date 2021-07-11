package com.example.temanautis.Model;

import java.util.List;

public class YayasanResponse {
    private int code;
    private String message;
    private List<YayasanModel> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<YayasanModel> getData() {
        return data;
    }

    public void setData(List<YayasanModel> data) {
        this.data = data;
    }
}
