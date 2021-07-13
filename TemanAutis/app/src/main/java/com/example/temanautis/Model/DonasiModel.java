package com.example.temanautis.Model;

public class DonasiModel {
    private int id;
    private String nama_donasi, yayasan, keterangan, banner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_donasi() {
        return nama_donasi;
    }

    public void setNama_donasi(String nama_donasi) {
        this.nama_donasi = nama_donasi;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getYayasan() {
        return yayasan;
    }

    public void setYayasan(String yayasan) {
        this.yayasan = yayasan;
    }
}
