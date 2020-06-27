package com.leadevs.misslab.models;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class Praktikum implements Serializable {
    private String id;
    private String name;
    private String code;
    private String class_room;
    private String semester;
    private String school_year;
    private String assistant_one;
    private String assistant_two;
    private String lecture;
    private String department;
    private String day;
    private String start_time;
    private String end_time;
    private String name_image;
    private String url_image;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Praktikum() {
    }

    public Praktikum(String id, String name, String code, String class_room, String semester, String school_year, String assistant_one, String assistant_two, String lecture, String department, String day, String start_time, String end_time, String name_image, String url_image, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.class_room = class_room;
        this.semester = semester;
        this.school_year = school_year;
        this.assistant_one = assistant_one;
        this.assistant_two = assistant_two;
        this.lecture = lecture;
        this.department = department;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
        this.name_image = name_image;
        this.url_image = url_image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClass_room() {
        return class_room;
    }

    public void setClass_room(String class_room) {
        this.class_room = class_room;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSchool_year() {
        return school_year;
    }

    public void setSchool_year(String school_year) {
        this.school_year = school_year;
    }

    public String getAssistant_one() {
        return assistant_one;
    }

    public void setAssistant_one(String assistant_one) {
        this.assistant_one = assistant_one;
    }

    public String getAssistant_two() {
        return assistant_two;
    }

    public void setAssistant_two(String assistant_two) {
        this.assistant_two = assistant_two;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
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
