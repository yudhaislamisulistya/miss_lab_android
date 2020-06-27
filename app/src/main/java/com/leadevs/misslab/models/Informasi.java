package com.leadevs.misslab.models;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class Informasi implements Serializable {
    private String id;
    private String title;
    private String content;
    private String to;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Informasi(String id, String title, String content, String to, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.to = to;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Informasi() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
