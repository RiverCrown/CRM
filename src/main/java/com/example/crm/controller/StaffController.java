package com.example.crm.controller;

import com.example.crm.domain.Customer;
import com.example.crm.domain.Staff;
import com.example.crm.service.CustomerServiceImpl;
import com.example.crm.service.StaffService;
import com.example.crm.service.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/staff")
public class StaffController {

    private StaffServiceImpl staffService;//声明接口变量，自动装配会在容器中查找并引用实现了这个接口的实例
    private CustomerServiceImpl customerService;

    @Autowired
    public StaffController(StaffServiceImpl staffService, CustomerServiceImpl customerService) {
        this.customerService = customerService;
        this.staffService = staffService;
    }

    @RequestMapping(value = "/modifyStaff")
    @ResponseBody
    public Staff modifyStaff(HttpSession session, Staff staff){
        Staff originalStaff = (Staff)session.getAttribute("staffObj");
        originalStaff.setName(staff.getName());
        originalStaff.setAddress(staff.getAddress());
        originalStaff.setAge(staff.getAge());
        originalStaff.setEmail(staff.getEmail());
        originalStaff.setPhoneNumber(staff.getPhoneNumber());
        originalStaff.setSex(staff.isSex());
        session.setAttribute("staffObj", originalStaff);
        staffService.modifyStaff(originalStaff);
        return originalStaff;
    }

    @RequestMapping(value = "/modifyStaffPassword")
    @ResponseBody
    public String modifyStaffPassword(@RequestParam(value = "originalPassword") String originalPassword,
                                      @RequestParam(value = "newPassword") String newPassword,
                                      HttpSession session, HttpServletResponse response) {
        Staff originalStaff = (Staff)session.getAttribute("staffObj");
        if (staffService.modifyStaffPassword(response, originalPassword, newPassword, originalStaff.getId())){
            originalStaff.setPassword(newPassword);
            session.setAttribute("staffObj", originalStaff);
            return "success";
        }
        return "failure";
    }

    @RequestMapping(value = "/findAllStaffsButSelf")
    @ResponseBody
    public List<Staff> findAllStaffsButSelf(HttpSession session) {
        List<Staff> staffs = staffService.findAllStaffs();
        Staff user = (Staff)session.getAttribute("staffObj");
        for (int i=0; i<staffs.size(); i++) {
            if (user.getId() == staffs.get(i).getId()) {
                staffs.remove(i);
                break;
            }
        }
        return staffs;
    }

    @RequestMapping(value = "/getStaffById")
    @ResponseBody
    public Staff getStaffById(@RequestParam(value = "staffId") int staffId) {
        return staffService.getStaff(staffId);
    }

}
