package com.cuscatlan.test.shoppingcartapi.service;
import com.cuscatlan.test.shoppingcartapi.model.OrderDetail;
import com.cuscatlan.test.shoppingcartapi.model.Product;
import com.cuscatlan.test.shoppingcartapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public OrderDetail createOrder(OrderDetail orderDetail) {
        // Implement your business logic for creating an order here
        return orderRepository.save(orderDetail);
    }

    public List<OrderDetail> getAllOrders(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }
    public OrderDetail getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public List<Product> getOrderDetails(Long id) {
        OrderDetail orderDetail = orderRepository.findById(id).orElse(null);
        if (orderDetail != null) {
            return orderDetail.getProducts();
        }
        return null;
    }
}

