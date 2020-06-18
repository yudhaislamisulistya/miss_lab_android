package com.leadevs.misslab.models;

public class Dosen {
    private int id;
    private String namaLengkap;
    private String nidn;
    private String jenisKelamin;
    private String phone;
    private String foto;

    public Dosen(int id, String namaLengkap, String nidn, String jenisKelamin, String phone, String foto) {
        this.id = id;
        this.namaLengkap = namaLengkap;
        this.nidn = nidn;
        this.jenisKelamin = jenisKelamin;
        this.phone = phone;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
