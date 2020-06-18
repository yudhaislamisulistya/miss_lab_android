package com.leadevs.misslab.models;

public class Informasi {
    private int id;
    private String judul;
    private String konten;
    private String untuk;
    private String waktu_buat;
    private String waktu_ubah;

    public Informasi(int id, String judul, String konten, String untuk, String waktu_buat, String waktu_ubah) {
        this.id = id;
        this.judul = judul;
        this.konten = konten;
        this.untuk = untuk;
        this.waktu_buat = waktu_buat;
        this.waktu_ubah = waktu_ubah;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getUntuk() {
        return untuk;
    }

    public void setUntuk(String untuk) {
        this.untuk = untuk;
    }

    public String getWaktu_buat() {
        return waktu_buat;
    }

    public void setWaktu_buat(String waktu_buat) {
        this.waktu_buat = waktu_buat;
    }

    public String getWaktu_ubah() {
        return waktu_ubah;
    }

    public void setWaktu_ubah(String waktu_ubah) {
        this.waktu_ubah = waktu_ubah;
    }
}
