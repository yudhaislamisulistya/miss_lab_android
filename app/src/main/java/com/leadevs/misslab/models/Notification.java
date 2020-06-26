package com.leadevs.misslab.models;

public class Notification {
    private int id;
    private String namaPraktikum;
    private String notif;

    public Notification(int id, String namaPraktikum, String notif) {
        this.id = id;
        this.namaPraktikum = namaPraktikum;
        this.notif = notif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPraktikum() {
        return namaPraktikum;
    }

    public void setNamaPraktikum(String namaPraktikum) {
        this.namaPraktikum = namaPraktikum;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }
}
