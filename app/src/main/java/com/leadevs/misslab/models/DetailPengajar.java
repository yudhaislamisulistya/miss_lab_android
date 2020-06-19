package com.leadevs.misslab.models;

public class DetailPengajar {
    private int id;
    private String statusAbsen;
    private String namaPraktikum;
    private String hariWaktuPraktikum;
    private String ruanganPraktikum;
    private String status;

    public DetailPengajar(int id, String statusAbsen, String namaPraktikum, String hariWaktuPraktikum, String ruanganPraktikum, String status) {
        this.id = id;
        this.statusAbsen = statusAbsen;
        this.namaPraktikum = namaPraktikum;
        this.hariWaktuPraktikum = hariWaktuPraktikum;
        this.ruanganPraktikum = ruanganPraktikum;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusAbsen() {
        return statusAbsen;
    }

    public void setStatusAbsen(String statusAbsen) {
        this.statusAbsen = statusAbsen;
    }

    public String getNamaPraktikum() {
        return namaPraktikum;
    }

    public void setNamaPraktikum(String namaPraktikum) {
        this.namaPraktikum = namaPraktikum;
    }

    public String getHariWaktuPraktikum() {
        return hariWaktuPraktikum;
    }

    public void setHariWaktuPraktikum(String hariWaktuPraktikum) {
        this.hariWaktuPraktikum = hariWaktuPraktikum;
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
