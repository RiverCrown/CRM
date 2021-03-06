package com.example.crm.domain;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private int id;
    private boolean isCompany;
    private String tag;
    private boolean isPotential;
    private String address;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact representative;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "salesman_id")
    private Staff salesman;
    private String mainBusiness;
    private String name;
    private int type;

    public Customer(){

    }

    public Staff getSalesman() {
        return salesman;
    }

    public void setSalesman(Staff salesman) {
        this.salesman = salesman;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Contact getRepresentative() {
        return representative;
    }

    public void setRepresentative(Contact representative) {
        this.representative = representative;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public boolean isPotential() {
        return isPotential;
    }

    public void setPotential(boolean potential) {
        isPotential = potential;
    }
}
