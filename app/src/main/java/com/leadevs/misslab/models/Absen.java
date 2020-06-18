package com.leadevs.misslab.models;

public class Absen {
    private int id;
    private int id_praktikum;
    private String namaSekarang;
    private String namaPraktikum;
    private String mulaiAkhirPraktikum;
    private String ruanganPraktikum;
    private String status;

    public Absen(int id, String namaSekarang, String namaPraktikum, String mulaiAkhirPraktikum, String ruanganPraktikum, String status) {
        this.id = id;
        this.namaSekarang = namaSekarang;
        this.namaPraktikum = namaPraktikum;
        this.mulaiAkhirPraktikum = mulaiAkhirPraktikum;
        this.ruanganPraktikum = ruanganPraktikum;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_praktikum() {
        return id_praktikum;
    }

    public void setId_praktikum(int id_praktikum) {
        this.id_praktikum = id_praktikum;
    }

    public String getNamaSekarang() {
        return namaSekarang;
    }

    public void setNamaSekarang(String namaSekarang) {
        this.namaSekarang = namaSekarang;
    }

    public String getNamaPraktikum() {
        return namaPraktikum;
    }

    public void setNamaPraktikum(String namaPraktikum) {
        this.namaPraktikum = namaPraktikum;
    }

    public String getMulaiAkhirPraktikum() {
        return mulaiAkhirPraktikum;
    }

    public void setMulaiAkhirPraktikum(String mulaiAkhirPraktikum) {
        this.mulaiAkhirPraktikum = mulaiAkhirPraktikum;
    }

    public String getRuanganPraktikum() {
        return ruanganPraktikum;
    }

    public void setRuanganPraktikum(String ruanganPraktikum) {
        this.ruanganPraktikum = ruanganPraktikum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
