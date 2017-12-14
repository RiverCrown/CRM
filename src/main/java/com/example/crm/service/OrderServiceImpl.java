package com.example.crm.service;

import com.example.crm.dao.CustomerRepository;
import com.example.crm.dao.OrderRepository;
import com.example.crm.dao.StaffRepository;
import com.example.crm.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private StaffRepository staffRepository;
    public final static int FILTER_HISTORY = 1;
    public final static int NOT_FILTER = 0;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            StaffRepository staffRepository) {
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.orderRepository = orderRepository;
    }

    public FollowOrderView getOrderById(int id) {
        FollowOrderView followOrderView = new FollowOrderView();
        List<FollowOrder> container = new ArrayList<>();
        FollowOrder followOrder = orderRepository.findOne(id);
        if (followOrder != null) {
            container.add(followOrder);
            followOrderView = transferFollowOrder(container, 0).get(0);
        }
        return followOrderView;
    }

    public FollowOrder findOrderById(int id) {
        return orderRepository.findOne(id);
    }

    public FollowOrder addOrder(FollowOrder followOrder) {
        followOrder = orderRepository.save(followOrder);
        followOrder.setGroupId(followOrder.getId());

        return orderRepository.save(followOrder);
    }

    public List<FollowOrderView> getHistoryByGroupId(int groupId) {
        return transferFollowOrder(orderRepository.findByGroupId(groupId), 0);
    }

    public FollowOrderView updateOrder(FollowOrder followOrder) {
        followOrder = orderRepository.save(followOrder);
        List<FollowOrder> container = new ArrayList<>();
        container.add(followOrder);
        return transferFollowOrder(container, 0).get(0);
    }

    public List<FollowOrderView> transferFollowOrder(List<FollowOrder> followOrders, int filterOption) {
        List<FollowOrderView> followOrderViews = new ArrayList<>();
        if (followOrders == null)
            return followOrderViews;
        for (FollowOrder followOrder : followOrders) {
            boolean isGroup = false;
            FollowOrderView temp = new FollowOrderView();
            List<CommentView> commentViews = new ArrayList<>();
            temp.setId(followOrder.getId());
            temp.setDigest(followOrder.getDigest());
            temp.setSalesmanName(staffRepository.findOne(followOrder.getSalesmanId()).getName());
            temp.setCustomerName(customerRepository.findOne(followOrder.getCustomerId()).getName());
            temp.setStartDate(followOrder.getStartDate());
            temp.setEndDate(followOrder.getEndDate());
            temp.setLatestPushDate(followOrder.getLatestPushDate());
            temp.setNextPushDate(followOrder.getNextPushDate());
            temp.setDetail(followOrder.getDetail());
            temp.setExpectedEndDate(followOrder.getExpectedEndDate());
            temp.setExpectedIncome(followOrder.getExpectedIncome());
            temp.setPossibility(followOrder.getPossibility());
            temp.setStatus(followOrder.getStatus());
            temp.setPhase(followOrder.getPhase());
            temp.setProduct(followOrder.getProduct());
            temp.setGroupId(followOrder.getGroupId());
            temp.setCustomerId(followOrder.getCustomerId());
            temp.setSalesmanId(followOrder.getSalesmanId());
            if (followOrder.getComments() != null) {
                for (int i=0; i<followOrder.getComments().size(); i++) {
                    CommentView commentView = new CommentView();
                    commentView.setId(followOrder.getComments().get(i).getId());
                    commentView.setContent(followOrder.getComments().get(i).getContent());
                    commentView.setDate(followOrder.getComments().get(i).getDate());
                    commentView.setReviewerId(followOrder.getComments().get(i).getReviewerId());
                    commentView.setReviewerName(staffRepository.findOne(followOrder.getComments().get(i).getReviewerId()).getName());
                    commentViews.add(commentView);
                }
            }
            temp.setComments(commentViews);
            if (filterOption == 1) {
                for (int i=0; i<followOrderViews.size(); i++) {
                    if (followOrderViews.get(i).getGroupId().equals(temp.getGroupId()))
                    {
                        isGroup = true;
                        followOrderViews.set(i, temp);
                        break;
                    }
                }
                if (!isGroup)
                    followOrderViews.add(temp);
            } else {
                followOrderViews.add(temp);
            }
        }
        return followOrderViews;
    }

    public List<FollowOrderView> searchOrder(String by, String status,
                                             String phase, String input) {

        if (input.equals(""))
            return transferFollowOrder((List<FollowOrder>) orderRepository.findAll(), 1);
        ArrayList<FollowOrder> result = new ArrayList<>();
        switch (by) {
            case "number":
                try {
                    return transferFollowOrder(orderRepository.findById(Integer.parseInt(input)), 1);
                } catch (NumberFormatException e) {
                    return new ArrayList<>();
                }
            case "customer":
                ArrayList<Customer> customerNameResult = customerRepository.findByName(input);
                if (status.equals("0") && phase.equals("0")) {
                    for (Customer customer : customerNameResult) {
                        result.addAll(orderRepository.findByCustomerId(customer.getId()));
                    }
                    return transferFollowOrder(result, 1);
                }
                if (!status.equals("0") && !phase.equals("0")) {
                    for (Customer customer : customerNameResult) {
                        result.addAll(orderRepository.findByCustomerIdAndStatusAndPhase(customer.getId(), Integer.parseInt(status), Integer.parseInt(phase)));
                    }
                    return transferFollowOrder(result, 1);
                }
                if (!status.equals("0") && phase.equals("0")) {
                    for (Customer customer : customerNameResult) {
                        result.addAll(orderRepository.findByCustomerIdAndStatus(customer.getId(), Integer.parseInt(status)));
                    }
                    return transferFollowOrder(result, 1);
                }
                if (status.equals("0") && !phase.equals("0")) {
                    for (Customer customer : customerNameResult) {
                        result.addAll(orderRepository.findByCustomerIdAndPhase(customer.getId(), Integer.parseInt(phase)));
                    }
                    return transferFollowOrder(result, 1);
                }
            case "salesman":
                Staff staff = staffRepository.findStaffByName(input);
                if (status.equals("0") && phase.equals("0")) {
                    return transferFollowOrder(orderRepository.findBySalesmanId(staff.getId()), 1);
                }
                if (!status.equals("0") && !phase.equals("0")) {
                    return transferFollowOrder(orderRepository.findBySalesmanIdAndStatusAndPhase(staff.getId(), Integer.parseInt(status), Integer.parseInt(phase)), 1);
                }
                if (!status.equals("0") && phase.equals("0")) {
                    return transferFollowOrder(orderRepository.findBySalesmanIdAndStatus(staff.getId(), Integer.parseInt(status)), 1);
                }
                if (status.equals("0") && !phase.equals("0")) {
                    return transferFollowOrder(orderRepository.findBySalesmanIdAndPhase(staff.getId(), Integer.parseInt(phase)), 1);
                }
            default:
                return transferFollowOrder(result, 1);
        }
    }

    public void removeOrder(int id) {
        orderRepository.delete(id);
    }

    public void transferOrder(int staffId, int orderId) {
        FollowOrder followOrder = orderRepository.findOne(orderId);
        followOrder.setSalesmanId(staffId);
        orderRepository.save(followOrder);
    }
}
