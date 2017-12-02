package com.example.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/business")
public class BusinessController {

    @RequestMapping(value = "/orderManagement")
    public String orderManagement(){
        return "orderManagement";
    }

    @RequestMapping(value = "/routeManagement")
    public String routeManagement(){
        return "routeManagement";
    }
}
