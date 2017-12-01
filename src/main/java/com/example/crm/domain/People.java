package com.example.crm.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class People {
    private String name;
    private boolean sex;
    private int age;
    private String phoneNumber;
    private String address;
    private String email;

    public People(){

    }

    People(String name, boolean sex, String phoneNumber) {
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
