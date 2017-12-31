package com.example.crm.service;

import com.example.crm.dao.CustomerRepository;
import com.example.crm.dao.DepartureFormRepository;
import com.example.crm.dao.OrderRepository;
import com.example.crm.dao.StaffRepository;
import com.example.crm.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    private StaffRepository staffRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private DepartureFormRepository departureFormRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository, OrderRepository orderRepository,
                            CustomerRepository customerRepository, DepartureFormRepository departureFormRepository) {
        this.orderRepository = orderRepository;
        this.departureFormRepository = departureFormRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff modifyStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public boolean createStaff(Staff staff) {
        if (staffRepository.findStaffByName(staff.getName()) != null)
            return false;
        else
        {
            staff.setStatus("在职");
            staff.setPassword("1234");
            staffRepository.save(staff);
            return true;
        }

    }

    public boolean deleteStaff(int staffId) {
        Staff staff = staffRepository.findOne(staffId);
        List<Customer> customers = customerRepository.findBySalesman(staff.getName());
        List<FollowOrder> followOrders = orderRepository.findBySalesmanId(staffId);
        if ((customers != null && customers.size() != 0) || (followOrders != null && followOrders.size() != 0))
            return false;
        else {
            DepartureForm departureForm = departureFormRepository.findDepartureFormByStaffId(staffId);
            if (departureForm != null)
                departureFormRepository.delete(departureForm);
            staffRepository.delete(staffId);
            return true;
        }

    }

    public List<Staff> findAllStaffs() {
        return (ArrayList<Staff>)staffRepository.findAll();
    }

    public List<Staff> findAllOnTheJobStaffs() {
        return staffRepository.findByStatus("在职");
    }

    public void modifyRoute(List<Route> routes, Route route) {
        for (int i=0; i<routes.size(); i++) {
            if (routes.get(i).getId() == route.getId()) {
                routes.set(i, route);
                break;
            }
        }
    }

    public void auditDeparture(int formId) {
        DepartureForm departureForm = departureFormRepository.findOne(formId);
        Staff staff = staffRepository.findOne(departureForm.getStaffId());
        staff.setStatus("离职交接");
        departureForm.setAudited(true);
        departureFormRepository.save(departureForm);
    }

    public void rejectDeparture(int formId) {
        DepartureForm departureForm = departureFormRepository.findOne(formId);
        Staff staff = staffRepository.findOne(departureForm.getStaffId());
        staff.setStatus("在职（离职申请驳回）");
        departureFormRepository.delete(formId);
    }

    public List<DepartureFormView> getAllDepartureForms() {
        List<DepartureForm> departureForms = (List<DepartureForm>)departureFormRepository.findAll();
        List<DepartureFormView> departureFormViews = new ArrayList<>();
        for (DepartureForm departureForm : departureForms) {
            DepartureFormView departureFormView = new DepartureFormView(departureForm);
            departureFormView.setStaffName(staffRepository.findOne(departureForm.getStaffId()).getName());
            departureFormView.setHandoverStaffName(staffRepository.findOne(departureForm.getHandoverStaffId()).getName());
            departureFormViews.add(departureFormView);
        }
        return departureFormViews;
    }

    public boolean hasSubmitted(HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staffObj");
        List<DepartureForm> departureForms = departureFormRepository.findByStaffId(staff.getId());
        return (departureForms != null && departureForms.size() != 0);
    }

    public void submitResignForm(DepartureForm departureForm) {
        Staff staff = staffRepository.findOne(departureForm.getStaffId());
        staff.setStatus("离职申请中");
        staffRepository.save(staff);
        departureFormRepository.save(departureForm);
    }

    public void removeRoute(List<Route> routes, int routeId) {
        for (int i=0; i<routes.size(); i++) {
            if (routes.get(i).getId() == routeId) {
                routes.remove(i);
                break;
            }
        }
    }

    @Override
    public boolean modifyStaffPassword(HttpServletResponse response, String originalPassword, String newPassword, Integer id){
        Staff staff = staffRepository.findOne(id);
        if (staff.getPassword().equals(originalPassword)){
            staff.setPassword(newPassword);
            Cookie passwordCookie = new Cookie("password", newPassword);
            passwordCookie.setMaxAge(60 * 60 * 24);
            passwordCookie.setPath("/");
            response.addCookie(passwordCookie);
            return true;
        }
        return false;
    }

    @Override
    public Staff getStaff(Integer id) {
        return staffRepository.findOne(id);
    }

    @Override
    public boolean validateStaffAccount(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("account");
        String password = request.getParameter("password");
        Cookie userNameCookie = new Cookie("account", userName);
        Cookie passwordCookie = new Cookie("password", password);
        Staff staff = staffRepository.findStaffByName(userName);
        if (staff != null && staff.getPassword().equals(password)){
            if (request.getParameter("remember") != null) {
                userNameCookie.setMaxAge(60 * 60 * 24);
                passwordCookie.setMaxAge(60 * 60 * 24);
                userNameCookie.setPath("/");
                passwordCookie.setPath("/");
            } else {
                userNameCookie.setMaxAge(0);
                userNameCookie.setPath("/");
                passwordCookie.setMaxAge(0);
                passwordCookie.setPath("/");
            }
            response.addCookie(userNameCookie);
            response.addCookie(passwordCookie);
            request.getSession().setAttribute("staffObj", staff);
            request.getSession().setAttribute("isLogin", true);
            request.getSession().setAttribute("fromInside", true);
            return true;
        }
        request.getSession().setAttribute("isLogin", false);
        request.getSession().setAttribute("fromInside", false);
        return false;
    }
}
