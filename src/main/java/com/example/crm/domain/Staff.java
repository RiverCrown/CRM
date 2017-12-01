package com.example.crm.domain;

import javax.persistence.*;


@Entity
@Table(name = "staff")
public class Staff extends People{

    @Id
    @GeneratedValue
    private Integer id;
    private String role;
    private String password;

    public Staff(){

    }

    public Staff(String name, boolean sex, String phoneNumber, int id, String role, String password){
        super(name, sex, phoneNumber);
        this.id = id;
        this.role = role;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
