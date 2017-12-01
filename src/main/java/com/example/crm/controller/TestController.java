package com.example.crm.controller;

import com.example.crm.domain.Customer;
import com.example.crm.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {

    @RequestMapping("/")
    public String root(HttpSession session){
        session.setAttribute("isLogin", true);
        return "login";
    }

    @RequestMapping("/orderManagement")
    public String test1(){
        return "orderManagement";
    }

    @RequestMapping("/routeManagement")
    public String test2(){
        return "routeManagement";
    }

    @RequestMapping("/customerManagement")
    public String test3(){
        return "customerManagement";
    }



}
