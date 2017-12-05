package com.example.crm.controller;

import com.example.crm.dao.RouteRepository;
import com.example.crm.service.RouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

    private RouteServiceImpl routeService;

    @Autowired
    public RouteController(RouteServiceImpl routeService) {
        this.routeService = routeService;
    }

    @RequestMapping(value = "/")
    public String test() {
        return "";
    }
}
