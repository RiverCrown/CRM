package com.example.crm.domain;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tags {

    @Id
    @GeneratedValue
    private int id;
    private int customerId;
    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "tagTemplate_id")
    private TagTemplate tagTemplate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public TagTemplate getTagTemplate() {
        return tagTemplate;
    }

    public void setTagTemplate(TagTemplate tagTemplate) {
        this.tagTemplate = tagTemplate;
    }
}
