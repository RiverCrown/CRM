package com.example.crm.dao;

import com.example.crm.domain.Comment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "comment")
@Qualifier("commentRepository")
public interface CommentRepository extends CrudRepository<Comment, Integer>{
}
