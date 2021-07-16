package com.example.temanautis.Model;

import java.util.List;

public class ListDonateResponse {
    private int code;
    private String message;
    private List<ListDonateModel> data;

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

    public List<ListDonateModel> getData() {
        return data;
    }

    public void setData(List<ListDonateModel> data) {
        this.data = data;
    }
}
