package com.example.crm.dao;

import com.example.crm.domain.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "customer")
@Qualifier("customerRepository")
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
