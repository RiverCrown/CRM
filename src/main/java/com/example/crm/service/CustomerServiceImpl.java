package com.example.crm.service;

import com.example.crm.dao.CustomerRepository;
import com.example.crm.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class CustomerServiceImpl {

    private CustomerRepository customerRepository;

    @Autowired
    CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer findCustomer(Integer id){
        return customerRepository.findOne(id);
    }

    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public ArrayList<Customer> findAllCustomer(){
        return (ArrayList<Customer>) customerRepository.findAll();
    }
}
