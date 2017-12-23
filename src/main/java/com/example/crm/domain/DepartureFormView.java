package com.example.crm.domain;

public class DepartureFormView extends DepartureForm {

    private String staffName;
    private String handoverStaffName;

    public DepartureFormView () {

    }

    public DepartureFormView (DepartureForm departureForm) {
        if (departureForm !=null) {
            this.setId(departureForm.getId());
            this.setApplicationDate(departureForm.getApplicationDate());
            this.setAdvise(departureForm.getAdvise());
            this.setAudited(departureForm.isAudited());
            this.setDepartureDate(departureForm.getDepartureDate());
            this.setHandoverStaffId(departureForm.getHandoverStaffId());
            this.setReason(departureForm.getReason());
            this.setStaffId(departureForm.getStaffId());
        }

    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getHandoverStaffName() {
        return handoverStaffName;
    }

    public void setHandoverStaffName(String handoverStaffName) {
        this.handoverStaffName = handoverStaffName;
    }
}
