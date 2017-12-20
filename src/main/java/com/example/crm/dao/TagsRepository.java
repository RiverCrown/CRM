package com.example.crm.dao;

import com.example.crm.domain.TagTemplate;
import com.example.crm.domain.Tags;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "tags")
@Qualifier("tagsRepository")
public interface TagsRepository extends CrudRepository<Tags, Integer>{

    List<Tags> findByCustomerId(int customerId);
}
