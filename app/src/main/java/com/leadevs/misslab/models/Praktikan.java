package com.leadevs.misslab.models;

public class Praktikan {
    private int id;
    private String stambuk;
    private String namaLengkap;
    private String programStudi;
    private String angkatan;
    private String noTelp;
    private String alamat;

    public Praktikan(int id, String stambuk, String namaLengkap, String programStudi, String angkatan, String noTelp, String alamat) {
        this.id = id;
        this.stambuk = stambuk;
        this.namaLengkap = namaLengkap;
        this.programStudi = programStudi;
        this.angkatan = angkatan;
        this.noTelp = noTelp;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStambuk() {
        return stambuk;
    }

    public void setStambuk(String stambuk) {
        this.stambuk = stambuk;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(String programStudi) {
        this.programStudi = programStudi;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
