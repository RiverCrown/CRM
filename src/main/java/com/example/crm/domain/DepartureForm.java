package com.example.crm.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "departureForm")
public class DepartureForm {

    @Id
    @GeneratedValue
    private int id;
    private int staffId;
    private String reason;
    private LocalDate applicationDate;
    private int handoverStaffId;
    private LocalDate departureDate;
    private String advise;
    private boolean isAudited;

    public boolean isAudited() {
        return isAudited;
    }

    public void setAudited(boolean audited) {
        isAudited = audited;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public int getHandoverStaffId() {
        return handoverStaffId;
    }

    public void setHandoverStaffId(int handoverStaffId) {
        this.handoverStaffId = handoverStaffId;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        if (departureDate != null && !departureDate.equals(""))
            this.departureDate = LocalDate.parse(departureDate);
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}
