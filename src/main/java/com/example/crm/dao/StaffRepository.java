package com.example.crm.dao;

import com.example.crm.domain.Staff;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "staff")
@Qualifier("staffRepository")
public interface StaffRepository extends CrudRepository<Staff, Integer> {

    Staff findOne(Integer id);
    Staff findStaffByName(String name);
}
