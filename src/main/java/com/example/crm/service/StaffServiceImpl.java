package com.example.crm.service;

import com.example.crm.dao.CustomerRepository;
import com.example.crm.dao.StaffRepository;
import com.example.crm.domain.Customer;
import com.example.crm.domain.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    private StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff modifyStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void createStaff(String name, boolean sex) {

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
            return true;
        }
        request.getSession().setAttribute("isLogin", false);
        return false;
    }
}
