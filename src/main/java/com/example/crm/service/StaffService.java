package com.example.crm.service;

import com.example.crm.domain.Customer;
import com.example.crm.domain.Staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface StaffService {

    public Staff modifyStaff(Staff staff);
    public boolean createStaff(Staff staff);
    public Staff getStaff(Integer id);
    public boolean validateStaffAccount(HttpServletRequest request, HttpServletResponse response);
    public boolean modifyStaffPassword(HttpServletResponse response, String originalPassword, String newPassword, Integer id);
}
