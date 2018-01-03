package com.example.crm.dao;

import com.example.crm.domain.FollowOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "followOrder")
@Qualifier("followOrderRepository")
public interface OrderRepository extends CrudRepository<FollowOrder, Integer>{

    List<FollowOrder> findByGroupId(int groupId);

    List<FollowOrder> findById(int id);

    List<FollowOrder> findBySalesmanId(Integer salesmanId);

    List<FollowOrder> findBySalesmanIdAndStatusAndPhase(Integer salesmanId, Integer status, Integer phase);

    List<FollowOrder> findBySalesmanIdAndStatus(Integer salesmanId, Integer status);

    List<FollowOrder> findBySalesmanIdAndPhase(Integer salesmanId, Integer phase);

    List<FollowOrder> findByCustomerIdAndStatus(Integer customerId, Integer status);

    List<FollowOrder> findByCustomerIdAndPhase(Integer customerId, Integer phase);

    List<FollowOrder> findByCustomerIdAndStatusAndPhase(Integer customerId, Integer status, Integer phase);

    List<FollowOrder> findByCustomerId(Integer customerId);

    List<FollowOrder> findFollowOrderBySalesmanIdAndCustomerId(int staffId, int customerId);

}
