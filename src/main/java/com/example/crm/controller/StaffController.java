package com.example.crm.controller;

import com.example.crm.domain.Customer;
import com.example.crm.domain.Staff;
import com.example.crm.service.CustomerServiceImpl;
import com.example.crm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;


@Controller
@RequestMapping(value = "/staff")
    public class StaffController {

    private StaffService staffService;//声明接口变量，自动装配会在容器中查找并引用实现了这个接口的实例
    private CustomerServiceImpl customerService;

    @Autowired
    public StaffController(StaffService staffService, CustomerServiceImpl customerService) {
        this.customerService = customerService;
        this.staffService = staffService;
    }


    @RequestMapping(value = "/Create")
    public void createStaff(@RequestParam(value = "name", defaultValue = "cfb") String name,
                            @RequestParam(value = "sex", defaultValue = "true") boolean sex) {
        staffService.createStaff(name, sex);
    }

    @RequestMapping(value = "/Get")
    public Staff getStaff(@RequestParam(value = "id", defaultValue = "1") Integer id) {
        return staffService.getStaff(id);
    }

    @RequestMapping(value = "/mainPage")
    public String login(HttpServletRequest request, HttpServletResponse response){
        if (staffService.validateStaffAccount(request, response)){
            return "mainPage";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/personalInfo")
    public String test4(){
        return "personalInfo";
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

    @RequestMapping(value = "/addCustomer")
    @ResponseBody
    public String addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return "success";
    }

    @RequestMapping(value = "/searchCustomer")
    @ResponseBody
    public ArrayList<Customer> searchCustomer(@RequestParam(value = "searchOption1") String option1,
                                              @RequestParam(value = "searchOption2") String option2,
                                              @RequestParam(value = "searchInput") String input) {

        return customerService.searchCustomerByOption(option1, option2, input);
    }
}
