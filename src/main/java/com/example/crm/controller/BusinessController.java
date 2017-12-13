package com.example.crm.controller;

import com.example.crm.domain.*;
import com.example.crm.service.CommentServiceImpl;
import com.example.crm.service.OrderServiceImpl;
import com.example.crm.service.RouteServiceImpl;
import com.example.crm.service.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/business")
public class BusinessController {

    private RouteServiceImpl routeService;
    private StaffServiceImpl staffService;
    private OrderServiceImpl orderService;
    private CommentServiceImpl commentService;

    @Autowired
    public BusinessController(RouteServiceImpl routeService, StaffServiceImpl staffService,
                              OrderServiceImpl orderService, CommentServiceImpl commentService) {
        this.staffService = staffService;
        this.routeService = routeService;
        this.orderService = orderService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/addOrder")
    @ResponseBody
    public void addOrder(@RequestBody FollowOrder followOrder) {
        orderService.addOrder(followOrder);
    }

    @RequestMapping(value = "/newOrderPage")
    public String newOrderPage() {
        return "newOrder";
    }

    @RequestMapping(value = "/searchOrder")
    @ResponseBody
    public List<FollowOrderView> searchOrder(@RequestParam(value = "by") String by,
                                             @RequestParam(value = "status") String status,
                                             @RequestParam(value = "phase") String phase,
                                             @RequestParam(value = "input") String input) {
        return orderService.searchOrder(by, status, phase, input);
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

    @RequestMapping(value = "/orderInfo")
    public String orderInfo(@RequestParam(value = "id") int id,
                            Model model) {
        FollowOrderView followOrderView = orderService.getOrderById(id);
        List<FollowOrderView> history = orderService.getHistoryByGroupId(followOrderView.getId());
        model.addAttribute("orderHistory", history);
        model.addAttribute("order", followOrderView);
        return "orderInfo";
    }

    @RequestMapping(value = "/modifyOrder")
    @ResponseBody
    public String modifyOrder(@RequestBody FollowOrder followOrder) {
        return String.valueOf(orderService.updateOrder(followOrder).getId());
    }

    @RequestMapping(value = "/removeOrder")
    @ResponseBody
    public void removeOrder(@RequestParam(value = "id") int id) {
        orderService.removeOrder(id);
    }

    @RequestMapping(value = "/addComment")
    @ResponseBody
    public void addComment(Comment comment,
                           @RequestParam(value = "orderId") int orderId) {
        FollowOrder followOrder = orderService.findOrderById(orderId);
        followOrder.getComments().add(comment);
        orderService.updateOrder(followOrder);
    }
}
