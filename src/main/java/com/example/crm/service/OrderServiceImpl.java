package com.example.crm.service;

import com.example.crm.dao.CustomerRepository;
import com.example.crm.dao.OrderRepository;
import com.example.crm.dao.StaffRepository;
import com.example.crm.domain.Customer;
import com.example.crm.domain.FollowOrder;
import com.example.crm.domain.FollowOrderView;
import com.example.crm.domain.Staff;
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

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            StaffRepository staffRepository) {
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.orderRepository = orderRepository;
    }

    public void addOrUpdateOrder(FollowOrder followOrder) {
        orderRepository.save(followOrder);
    }

    private List<FollowOrderView> transferFollowOrder(List<FollowOrder> followOrders) {
        List<FollowOrderView> followOrderViews = new ArrayList<>();
        if (followOrders == null)
            return followOrderViews;
        for (FollowOrder followOrder : followOrders) {
            FollowOrderView temp = new FollowOrderView();
            switch (followOrder.getStatus()) {
                case 1:
                    temp.setStatus("跟踪");
                    break;
                case 2:
                    temp.setStatus("搁置");
                    break;
                case 3:
                    temp.setStatus("成功");
                    break;
                case 4:
                    temp.setStatus("失败");
                    break;
                case 5:
                    temp.setStatus("失效");
                    break;
                default:
                    temp.setStatus("无");
                    break;
            }
            switch (followOrder.getPhase()) {
                case 1:
                    temp.setPhase("1/7 初期沟通");
                    break;
                case 2:
                    temp.setPhase("2/7 立项评估");
                    break;
                case 3:
                    temp.setPhase("3/7 需求分析");
                    break;
                case 4:
                    temp.setPhase("4/7 方案制定");
                    break;
                case 5:
                    temp.setPhase("5/7 招投标竞争");
                    break;
                case 6:
                    temp.setPhase("6/7 商务谈判");
                    break;
                case 7:
                    temp.setPhase("7/7 合同签约");
                    break;
                default:
                    temp.setPhase("无");
                    break;
            }
            temp.setId(followOrder.getId());
            temp.setDigest(followOrder.getDigest());
            temp.setSalesmanName(staffRepository.findOne(followOrder.getSalesmanId()).getName());
            temp.setCustomerName(customerRepository.findOne(followOrder.getCustomerId()).getName());
            followOrderViews.add(temp);
        }
        return followOrderViews;
    }

    public List<FollowOrderView> searchOrder(String by, String status,
                                             String phase, String input) {

        if (input.equals(""))
            return transferFollowOrder((List<FollowOrder>) orderRepository.findAll());
        ArrayList<FollowOrder> result = new ArrayList<>();
        switch (by) {
            case "number":
                try {
                    return transferFollowOrder(orderRepository.findById(Integer.parseInt(input)));
                } catch (NumberFormatException e) {
                    return new ArrayList<>();
                }
            case "customer":
                ArrayList<Customer> customerNameResult = customerRepository.findByName(input);
                if (status.equals("0") && phase.equals("0")) {
                    for (Customer customer : customerNameResult) {
                        result.addAll(orderRepository.findByCustomerId(customer.getId()));
                    }
                    return transferFollowOrder(result);
                }
                if (!status.equals("0") && !phase.equals("0")) {
                    for (Customer customer : customerNameResult) {
                        result.addAll(orderRepository.findByCustomerIdAndStatusAndPhase(customer.getId(), Integer.parseInt(status), Integer.parseInt(phase)));
                    }
                    return transferFollowOrder(result);
                }
                if (!status.equals("0") && phase.equals("0")) {
                    for (Customer customer : customerNameResult) {
                        result.addAll(orderRepository.findByCustomerIdAndStatus(customer.getId(), Integer.parseInt(status)));
                    }
                    return transferFollowOrder(result);
                }
                if (status.equals("0") && !phase.equals("0")) {
                    for (Customer customer : customerNameResult) {
                        result.addAll(orderRepository.findByCustomerIdAndPhase(customer.getId(), Integer.parseInt(phase)));
                    }
                    return transferFollowOrder(result);
                }
            case "salesman":
                Staff staff = staffRepository.findStaffByName(input);
                if (status.equals("0") && phase.equals("0")) {
                    return transferFollowOrder(orderRepository.findBySalesmanId(staff.getId()));
                }
                if (!status.equals("0") && !phase.equals("0")) {
                    return transferFollowOrder(orderRepository.findBySalesmanIdAndStatusAndPhase(staff.getId(), Integer.parseInt(status), Integer.parseInt(phase)));
                }
                if (!status.equals("0") && phase.equals("0")) {
                    return transferFollowOrder(orderRepository.findBySalesmanIdAndStatus(staff.getId(), Integer.parseInt(status)));
                }
                if (status.equals("0") && !phase.equals("0")) {
                    return transferFollowOrder(orderRepository.findBySalesmanIdAndPhase(staff.getId(), Integer.parseInt(phase)));
                }
            default:
                return transferFollowOrder(result);
        }
    }
}
