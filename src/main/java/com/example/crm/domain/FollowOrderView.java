package com.example.crm.domain;

import java.time.LocalDateTime;
import java.util.List;

public class FollowOrderView {

    private int id;
    private Integer groupId;
    private Integer salesmanId;
    private String salesmanName;
    private Integer customerId;
    private String customerName;
    private Integer status;
    private String digest;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime latestPushDate;
    private LocalDateTime nextPushDate;
    private String detail;
    private LocalDateTime expectedEndDate;
    private Double expectedIncome;
    private Integer possibility;
    private Integer phase;
    private String product;
    private List<CommentView> comments;

    public FollowOrderView(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(Double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Integer getPossibility() {
        return possibility;
    }

    public void setPossibility(Integer possibility) {
        this.possibility = possibility;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public List<CommentView> getComments() {
        return comments;
    }

    public void setComments(List<CommentView> comments) {
        this.comments = comments;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getLatestPushDate() {
        return latestPushDate;
    }

    public void setLatestPushDate(LocalDateTime latestPushDate) {
        this.latestPushDate = latestPushDate;
    }

    public LocalDateTime getNextPushDate() {
        return nextPushDate;
    }

    public void setNextPushDate(LocalDateTime nextPushDate) {
        this.nextPushDate = nextPushDate;
    }

    public LocalDateTime getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(LocalDateTime expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }
}
