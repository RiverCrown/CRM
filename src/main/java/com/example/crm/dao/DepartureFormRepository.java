package com.example.crm.dao;

import com.example.crm.domain.DepartureForm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "departureForm")
@Qualifier("departureFormRepository")
public interface DepartureFormRepository extends CrudRepository<DepartureForm, Integer>{

    List<DepartureForm> findByStaffId(int id);
}
