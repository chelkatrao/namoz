package com.chelkatrao.model;

public class Time {
    String tongTime;
    String quyoshTime;
    String peshinTime;
    String asrTime;
    String shomTime;
    String xuftonTime;

    public Time(String tongTime, String quyoshTime, String peshinTime, String asrTime, String shomTime, String xuftonTime) {
        this.tongTime = tongTime;
        this.quyoshTime = quyoshTime;
        this.peshinTime = peshinTime;
        this.asrTime = asrTime;
        this.shomTime = shomTime;
        this.xuftonTime = xuftonTime;
    }

    public String getTongTime() {
        return tongTime;
    }

    public String getQuyoshTime() {
        return quyoshTime;
    }

    public String getPeshinTime() {
        return peshinTime;
    }

    public String getAsrTime() {
        return asrTime;
    }

    public String getShomTime() {
        return shomTime;
    }

    public String getXuftonTime() {
        return xuftonTime;
    }
}
