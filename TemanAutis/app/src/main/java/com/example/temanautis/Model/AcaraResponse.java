package com.example.temanautis.Model;

import java.util.List;

public class AcaraResponse {
    private int code;
    private String message;
    private List<AcaraModel> data;

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

    public List<AcaraModel> getData() {
        return data;
    }

    public void setData(List<AcaraModel> data) {
        this.data = data;
    }
}
