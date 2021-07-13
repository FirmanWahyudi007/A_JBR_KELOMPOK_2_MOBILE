package com.example.temanautis.Model;

import java.util.List;

public class DonasiResponse {
    private int code;
    private String message;
    private List<DonasiModel> data;

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

    public List<DonasiModel> getData() {
        return data;
    }

    public void setData(List<DonasiModel> data) {
        this.data = data;
    }
}
