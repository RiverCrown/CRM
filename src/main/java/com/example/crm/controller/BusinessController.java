package com.example.crm.controller;

import com.example.crm.domain.Route;
import com.example.crm.domain.Staff;
import com.example.crm.service.RouteServiceImpl;
import com.example.crm.service.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/business")
public class BusinessController {

    private RouteServiceImpl routeService;
    private StaffServiceImpl staffService;

    @Autowired
    public BusinessController(RouteServiceImpl routeService, StaffServiceImpl staffService) {
        this.staffService = staffService;
        this.routeService = routeService;
    }

    @RequestMapping(value = "/orderManagement")
    public String orderManagement(){
        return "orderManagement";
    }

    @RequestMapping(value = "/routeManagement")
    public String routeManagement(){
        return "routeManagement";
    }

    @RequestMapping(value = "/getRoutes")
    @ResponseBody
    public List<Route> getRoutes(@RequestParam(value = "color") String color,
                                 HttpSession session) {
        Staff staff = (Staff)session.getAttribute("staffObj");
        switch (color) {
            case "gray":
                return routeService.filterRoutes(0, staff.getRoutes());
            case "green":
                return routeService.filterRoutes(1, staff.getRoutes());
            case "yellow":
                return routeService.filterRoutes(2, staff.getRoutes());
            case "red":
                return routeService.filterRoutes(3, staff.getRoutes());
            default:
                return staff.getRoutes();
        }
    }

    @RequestMapping(value = "/addRoute")
    @ResponseBody
    public List<Route> addRoute(@RequestBody Route route, HttpSession session) {
        Staff staff = (Staff)session.getAttribute("staffObj");
        staff.getRoutes().add(route);
        staff = staffService.modifyStaff(staff);
        session.setAttribute("staffObj", staff);
        return staff.getRoutes();
    }

    @RequestMapping(value = "/modifyRoute")
    @ResponseBody
    public List<Route> modifyRoute(@RequestBody Route route, HttpSession session) {
        Staff staff = (Staff)session.getAttribute("staffObj");
        staffService.modifyRoute(staff.getRoutes(), route);
        staff = staffService.modifyStaff(staff);
        session.setAttribute("staffObj", staff);
        return staff.getRoutes();
    }

    @RequestMapping(value = "/deleteRoute")
    @ResponseBody
    public List<Route> deleteRoute(@RequestParam(value = "id") int id,
                                   HttpSession session) {
        Staff staff = (Staff)session.getAttribute("staffObj");
        staffService.removeRoute(staff.getRoutes(), id);
        staff = staffService.modifyStaff(staff);
        routeService.deleteRoute(id);
        session.setAttribute("staffObj", staff);
        return staff.getRoutes();
    }
}
