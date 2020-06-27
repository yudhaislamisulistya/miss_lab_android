package com.leadevs.misslab.models;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class ActiveLab implements Serializable {
    private String id;
    private String fullname;
    private String stambuk;
    private String status_active;
    private Timestamp created_at;
    private Timestamp updated_at;

    public ActiveLab(String id, String fullname, String stambuk, String status_active, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.fullname = fullname;
        this.stambuk = stambuk;
        this.status_active = status_active;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public ActiveLab(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
