package com.example.temanautis.Model;

import java.util.List;

public class DonateResponse {
    private int code;
    private String message;
    private List<DonateModel> data;

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

    public List<DonateModel> getData() {
        return data;
    }

    public void setData(List<DonateModel> data) {
        this.data = data;
    }
}
