package com.example.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/services")
public class ServicesController {

    @RequestMapping(value = "/servicesManagement")
    public String servicesManagement() {
        return "servicesManagement";
    }

    @RequestMapping(value = "/consultingServicesManagement")
    public String consultingServicesManagement() {
        return "consultingServicesManagement";
    }

}
