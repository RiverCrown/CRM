package com.example.crm.dao;


import com.example.crm.domain.Route;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "route")
@Qualifier("routeRepository")
public interface RouteRepository extends CrudRepository<Route, Integer>{
}
