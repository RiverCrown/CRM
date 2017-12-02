package com.example.crm.controller;

import com.example.crm.domain.Customer;
import com.example.crm.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return "success";
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
        Customer customer = customerService.findCustomer(customerId);
        model.addAttribute("customer", customer);
        return "customerInfo";
    }
}
