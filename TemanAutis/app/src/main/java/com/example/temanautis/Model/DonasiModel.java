package com.example.temanautis.Model;

import com.google.gson.annotations.SerializedName;

public class DonasiModel {
    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("is_active")
    private int isActive;

    @SerializedName("penerima")
    private String penerima;

    @SerializedName("banner")
    private String banner;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("nama_yayasan")
    private String namaYayasan;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("user")
    private int user;

    @SerializedName("nama_donasi")
    private String namaDonasi;

    @SerializedName("yayasan")
    private int yayasan;

    public void setKeterangan(String keterangan){
        this.keterangan = keterangan;
    }

    public String getKeterangan(){
        return keterangan;
    }

    public void setIsActive(int isActive){
        this.isActive = isActive;
    }

    public int getIsActive(){
        return isActive;
    }

    public void setPenerima(String penerima){
        this.penerima = penerima;
    }

    public String getPenerima(){
        return penerima;
    }

    public void setBanner(String banner){
        this.banner = banner;
    }

    public String getBanner(){
        return banner;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public void setNamaYayasan(String namaYayasan){
        this.namaYayasan = namaYayasan;
    }

    public String getNamaYayasan(){
        return namaYayasan;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }

    public String getTanggal(){
        return tanggal;
    }

    public void setUser(int user){
        this.user = user;
    }

    public int getUser(){
        return user;
    }

    public void setNamaDonasi(String namaDonasi){
        this.namaDonasi = namaDonasi;
    }

    public String getNamaDonasi(){
        return namaDonasi;
    }

    public void setYayasan(int yayasan){
        this.yayasan = yayasan;
    }

    public int getYayasan(){
        return yayasan;
    }
}
