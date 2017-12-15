package com.example.crm.controller;

import com.example.crm.domain.Customer;
import com.example.crm.domain.Staff;
import com.example.crm.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    private CustomerServiceImpl customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customerManagement")
    public String customerManagement(){
        return "customerManagement";
    }

    @RequestMapping(value = "/addCustomer")
    @ResponseBody
    public Customer addCustomer(@RequestBody Customer customer,
                              HttpSession session) {
        customer.setSalesman((Staff)session.getAttribute("staffObj"));
        return customerService.addOrUpdateCustomer(customer);
    }

    @RequestMapping(value = "/searchCustomer")
    @ResponseBody
    public ArrayList<Customer> searchCustomer(@RequestParam(value = "searchOption1") String option1,
                                              @RequestParam(value = "searchOption2") String option2,
                                              @RequestParam(value = "searchInput") String input) {

        return customerService.searchCustomerByOption(option1, option2, input);
    }

    @RequestMapping(value = "/customerInfo")
    public String customerInfo(@RequestParam(value = "customerId") int customerId,
                               Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "customerInfo";
    }

    @RequestMapping(value = "/getMainBusiness")
    @ResponseBody
    public ArrayList<String> getMainBusiness() {
        return customerService.getCustomerMainBusiness();
    }

    @RequestMapping(value = "/modifyCustomer")
    @ResponseBody
    public String modifyCustomer(@RequestBody Customer customer, HttpSession session) {
        customer.setSalesman((Staff)session.getAttribute("staffObj"));
        customerService.addOrUpdateCustomer(customer);
        return "customerInfo";
    }

    @RequestMapping(value = "/classifyCustomer")
    public String classifyCustomer() {
        return "classifyCustomer";
    }

    @RequestMapping(value = "/removeCustomer")
    @ResponseBody
    public String removeCustomer(int id) {
        customerService.removeCustomer(id);
        return "customerManagement";
    }

    @RequestMapping(value = "/getCustomerById")
    @ResponseBody
    public Customer getCustomerById(@RequestParam(value = "customerId") int customerId) {
        return customerService.getCustomerById(customerId);
    }

    @RequestMapping(value = "/transferCustomer")
    @ResponseBody
    public void transferCustomer(@RequestParam(value = "staffId") int staffId,
                                 @RequestParam(value = "customerId") int customerId) {
        customerService.transferCustomer(customerId, staffId);
    }
}
