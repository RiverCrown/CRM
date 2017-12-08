package com.example.crm.dao;

import com.example.crm.domain.Contact;
import com.example.crm.domain.Customer;
import com.example.crm.domain.Staff;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;

@Repository
@Table(name = "customer")
@Qualifier("customerRepository")
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    ArrayList<Customer> findByIdAndMainBusiness(int id, String mainBusiness);

    ArrayList<Customer> findByNameAndMainBusiness(String name, String mainBusiness);

    @Query("select customer from Customer customer join customer.representative contact where contact.name = ?1 and customer.mainBusiness = ?2")
    ArrayList<Customer> findByRepresentativeNameAndMainBusiness(String contactName, String mainBusiness);

    @Query("select customer from Customer customer join customer.salesman salesman where salesman.name = ?1 and customer.mainBusiness = ?2")
    ArrayList<Customer> findBySalesmanNameAndMainBusiness(String salesmanName, String mainBusiness);

    @Query("select distinct customer.mainBusiness from Customer customer")
    ArrayList<String> findMainBusiness();

    ArrayList<Customer> findById(int id);

    ArrayList<Customer> findByName(String name);

    @Query("select customer from Customer customer join customer.salesman salesman where salesman.name = ?1")
    ArrayList<Customer> findBySalesman(String salesmanName);

    @Query("select customer from Customer customer join customer.representative contact where contact.name = ?1")
    ArrayList<Customer> findByRepresentative(String contactName);
}
