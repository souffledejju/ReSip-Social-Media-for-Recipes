package com.example.resip;

import java.io.Serializable;

public class Step implements Serializable {
    private String title;
    private String time;
    private String detail;

    public Step(String title, String time, String detail) {
        this.title = title;
        this.time = time;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDetail() {
        return detail;
    }
}