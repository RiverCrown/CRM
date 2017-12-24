package com.example.crm.service;

import com.example.crm.dao.*;
import com.example.crm.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl {

    private CustomerRepository customerRepository;
    private StaffRepository staffRepository;
    private TagTemplateRepository tagTemplateRepository;
    private TagsRepository tagsRepository;
    private OrderRepository orderRepository;

    @Autowired
    CustomerServiceImpl(CustomerRepository customerRepository, StaffRepository staffRepository,
                        TagTemplateRepository tagTemplateRepository, TagsRepository tagsRepository,
                        OrderRepository orderRepository){
        this.orderRepository = orderRepository;
        this.tagsRepository = tagsRepository;
        this.tagTemplateRepository = tagTemplateRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }

    public Customer getCustomerById(Integer id){
        return customerRepository.findOne(id);
    }

    public Customer addOrUpdateCustomer(Customer customer){
        return customerRepository.save(customer);
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

    public void deleteCustomerTag(int tagsId) {
        tagsRepository.delete(tagsId);
    }

    public List<CustomerTagView> getAllCustomerWithTags() {
        List<CustomerTagView> customerTagViews = new ArrayList<>();
        List<Customer> customers = (ArrayList<Customer>)customerRepository.findAll();
        for (Customer customer : customers) {
            CustomerTagView customerTagView = new CustomerTagView();
            customerTagView.setCustomer(customer);
            customerTagView.setTags(tagsRepository.findByCustomerId(customer.getId()));
            customerTagViews.add(customerTagView);
        }
        return customerTagViews;
    }

    public ArrayList<String> getCustomerMainBusiness() {
        return customerRepository.findMainBusiness();
    }

    public boolean removeCustomer(int id) {
        List<FollowOrder> followOrders = orderRepository.findByCustomerId(id);
        List<Tags> tags = tagsRepository.findByCustomerId(id);
        if (followOrders != null && followOrders.size() != 0) {
            return false;
        } else {
            if (tags != null)
                tagsRepository.delete(tags);
            customerRepository.delete(id);
            return true;
        }
    }

    public void transferCustomer(int customerId, int staffId) {
        Staff staff = staffRepository.findOne(staffId);
        Customer customer = customerRepository.findOne(customerId);
        customer.setSalesman(staff);
        customerRepository.save(customer);
    }

    public void addTagTemplate(TagTemplate tagTemplate) {
        tagTemplateRepository.save(tagTemplate);
    }

    public List<TagTemplate> getAllTagTemplate() {
        return (List<TagTemplate>)tagTemplateRepository.findAll();
    }

    public void addTagToCustomer(int customerId, List<Integer> newTags) {
        newTags.remove(0);
        for (int i=0; i<newTags.size(); i++) {
            Tags tags = new Tags();
            tags.setCustomerId(customerId);
            tags.setTagTemplate(tagTemplateRepository.findOne(newTags.get(i)));
            tagsRepository.save(tags);
        }
    }

    public void modifyTagTemplate(TagTemplate tagTemplate) {
        tagTemplateRepository.save(tagTemplate);
    }

    public void deleteTagTemplate(int tagTemplateId) {
        List<Tags> tags = tagsRepository.findByTagTemplate(tagTemplateRepository.findOne(tagTemplateId));
        tagsRepository.delete(tags);
        tagTemplateRepository.delete(tagTemplateId);
    }
}
