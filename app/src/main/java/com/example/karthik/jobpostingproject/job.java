package com.example.karthik.jobpostingproject;

/**
 * Created by karthik on 3/15/2018.
 */

public class job {
    private int sno;
    private String city;
    private String tech;
    private String des;

    public job(int sno, String city, String tech, String des) {
        this.sno = sno;
        this.city = city;
        this.tech = tech;
        this.des = des;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
