package com.leadevs.misslab.models;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class Asisten implements Serializable {
    private String id;
    private String id_user;
    private String fullname;
    private String stambuk;
    private String status_active;
    private String gender;
    private String phone;
    private String address;
    private String name_image;
    private String url_image;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Asisten(String id, String id_user, String fullname, String stambuk, String status_active, String gender, String phone, String address, String name_image, String url_image, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.id_user = id_user;
        this.fullname = fullname;
        this.stambuk = stambuk;
        this.status_active = status_active;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.name_image = name_image;
        this.url_image = url_image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Asisten(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStambuk() {
        return stambuk;
    }

    public void setStambuk(String stambuk) {
        this.stambuk = stambuk;
    }

    public String getStatus_active() {
        return status_active;
    }

    public void setStatus_active(String status_active) {
        this.status_active = status_active;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName_image() {
        return name_image;
    }

    public void setName_image(String name_image) {
        this.name_image = name_image;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
