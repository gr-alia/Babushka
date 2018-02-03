package com.loop.babushka.model;

/**
 * Created by Alyona on 03.02.2018.
 */

public class User {

    private Integer id;

    private String fname;

    private String lname;

    private String phone;

    private String email;

    private String gender;

    private String pictureUrl;

    private String sessionToken;

    private Integer tokenExpiry;

    private String payModeStatus;

    private String currency;

    private String country;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Integer getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(Integer tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }



    public String getPayModeStatus() {
        return payModeStatus;
    }

    public void setPayModeStatus(String payModeStatus) {
        this.payModeStatus = payModeStatus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;}

    }
