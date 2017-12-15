package com.example.crm.controller;

import com.example.crm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SystemController {

    private StaffService staffService;

    @Autowired
    public SystemController(StaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping("/")
    public String login(HttpSession session){
        session.setAttribute("isLogin", true);
        session.setAttribute("fromInside", false);
        return "login";
    }

    @RequestMapping(value = "/mainPage")
    public String login(HttpServletRequest request, HttpServletResponse response){
        boolean fromInside = (boolean) request.getSession().getAttribute("fromInside");
        if (fromInside)
            return "mainPage";
        else if (staffService.validateStaffAccount(request, response)){
            return "mainPage";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/personalInfo")
    public String personal(){
        return "personalInfo";
    }

}
