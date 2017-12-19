package com.example.crm.domain;

import java.util.List;

public class CustomerTagView {

    private List<Tags> tags;
    private Customer customer;

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
