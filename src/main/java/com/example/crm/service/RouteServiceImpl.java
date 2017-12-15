package com.example.crm.service;

import com.example.crm.dao.RouteRepository;
import com.example.crm.dao.StaffRepository;
import com.example.crm.domain.Customer;
import com.example.crm.domain.FollowOrder;
import com.example.crm.domain.Route;
import com.example.crm.domain.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class RouteServiceImpl {

    private RouteRepository routeRepository;
    private StaffRepository staffRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository){
        this.routeRepository = routeRepository;
    }

    public List<Route> filterRoutes(int color, List<Route> allRoutes) {
        List<Route> routes = new ArrayList<>();
        for (Route temp : allRoutes) {
            if (color == temp.getTaskLevel())
                routes.add(temp);
        }
        return routes;
    }

    public Staff autoGenerateRoute(Staff staff, FollowOrder followOrder, Customer customer) {
        if (followOrder == null)
            return null;
        LocalDate now = LocalDate.now();
        LocalDate nextPushDate = followOrder.getNextPushDate();
        long deltDay = now.until(nextPushDate, ChronoUnit.DAYS);
        //联动日程模块
        Route route = new Route();
        route.setStart(nextPushDate.toString());
        route.setTitle("跟进编号为" + followOrder.getId() + "的订单");
        route.setDetail("跟进的客户为【" + customer.getName() + "】");
        route.setStatus(0);
        if (deltDay <= 7)
            route.setTaskLevel(3);
        else if (deltDay <= 14)
            route.setTaskLevel(2);
        else if (deltDay <= 21)
            route.setTaskLevel(1);
        else
            route.setTaskLevel(0);
        staff.getRoutes().add(route);
        return staff;
    }

    public void deleteRoute(int id) {
        routeRepository.delete(id);
    }


}
