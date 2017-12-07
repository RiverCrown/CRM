package com.example.crm.dao;

import com.example.crm.domain.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "followOrder")
@Qualifier("followOrderRepository")
public interface OrderRepository extends CrudRepository<Order, Integer>{
}
