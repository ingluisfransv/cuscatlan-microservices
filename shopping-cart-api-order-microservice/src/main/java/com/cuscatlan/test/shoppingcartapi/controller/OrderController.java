package com.cuscatlan.test.shoppingcartapi.controller;

import com.cuscatlan.test.shoppingcartapi.model.OrderDetail;
import com.cuscatlan.test.shoppingcartapi.model.Product;
import com.cuscatlan.test.shoppingcartapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }
    @PostMapping("/create")
    public ResponseEntity<OrderDetail> createOrder(@RequestBody OrderDetail orderDetail) {
        OrderDetail createdOrderDetail = orderService.createOrder(orderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderById(@PathVariable Long id) {
        OrderDetail orderDetail = orderService.getOrderById(id);
        if (orderDetail != null) {
            return ResponseEntity.ok(orderDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<List<Product>> getOrderDetails(@PathVariable Long id) {
        List<Product> orderDetails = orderService.getOrderDetails(id);
        if (orderDetails != null) {
            return ResponseEntity.ok(orderDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
