package com.example.crm.dao;

import com.example.crm.domain.TagTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "tagTemplate")
@Qualifier("tagTemplateRepository")
public interface TagTemplateRepository extends CrudRepository<TagTemplate, Integer>{
}
