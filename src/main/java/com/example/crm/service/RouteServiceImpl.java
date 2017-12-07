package com.example.crm.service;

import com.example.crm.dao.RouteRepository;
import com.example.crm.dao.StaffRepository;
import com.example.crm.domain.Route;
import com.example.crm.domain.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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

    public void deleteRoute(int id) {
        routeRepository.delete(id);
    }
}
