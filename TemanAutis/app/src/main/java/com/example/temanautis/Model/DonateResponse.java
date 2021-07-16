package com.example.temanautis.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DonateResponse {
    @SerializedName("donate")
    private DonateModel donateModel;

    public DonateModel getDonateModel() {
        return donateModel;
    }

    public void setDonateModel(DonateModel donateModel) {
        this.donateModel = donateModel;
    }
}
