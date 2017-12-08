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

    public void addOrUpdateCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public ArrayList<Customer> findAllCustomer(){
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    public ArrayList<Customer> searchCustomerByOption(String option1, String option2, String input){
        if (input.equals(""))
            return (ArrayList<Customer>)customerRepository.findAll();
        try{
            switch (option1) {
                case "number":
                    if (option2.equals(""))
                        return customerRepository.findById(Integer.parseInt(input));
                    else
                        return customerRepository.findByIdAndMainBusiness(Integer.parseInt(input), option2);
                case "name":
                    if (option2.equals(""))
                        return customerRepository.findByName(input);
                    else
                        return customerRepository.findByNameAndMainBusiness(input, option2);
                case "contact":
                    if (option2.equals(""))
                        return customerRepository.findByRepresentative(input);
                    else
                        return customerRepository.findByRepresentativeNameAndMainBusiness(input, option2);
                case "salesman":
                    if (option2.equals(""))
                        return customerRepository.findBySalesman(input);
                    else
                        return customerRepository.findBySalesmanNameAndMainBusiness(input, option2);
                default:
                    return (ArrayList<Customer>)customerRepository.findAll();
            }
        } catch (NumberFormatException e) {
            return new ArrayList<>();
        }
    }

    public ArrayList<String> getCustomerMainBusiness() {
        return customerRepository.findMainBusiness();
    }

    public void removeCustomer(int id) {
        customerRepository.delete(id);
    }

}
