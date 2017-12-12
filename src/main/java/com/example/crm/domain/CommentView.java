package com.example.crm.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class CommentView {

    private int id;
    private LocalDateTime date;
    private Integer reviewerId;
    private String reviewerName;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
