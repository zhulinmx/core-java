package com.basic.ch02;

import java.io.*;

public class Address implements Cloneable, Serializable {
    private int code;
    private String city;

    public Address(int code, String city) {
        this.code = code;
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "code=" + code +
                ", city='" + city + '\'' +
                '}';
    }
}