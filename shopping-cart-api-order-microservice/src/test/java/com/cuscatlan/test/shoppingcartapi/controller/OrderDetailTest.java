package com.cuscatlan.test.shoppingcartapi.controller;

import com.cuscatlan.test.shoppingcartapi.model.Customer;
import com.cuscatlan.test.shoppingcartapi.model.OrderDetail;
import com.cuscatlan.test.shoppingcartapi.model.Product;
import com.cuscatlan.test.shoppingcartapi.repository.OrderRepository;
import com.cuscatlan.test.shoppingcartapi.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderDetailTest {

    @InjectMocks
    private OrderService orderDetailService;

    @Mock
    private OrderRepository orderDetailRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrderDetail() {
        // Create objects for Customer and Product to the orderDetail
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        Product product1 = new Product();
        product1.setId(1);
        product1.setTitle("Product 1");
        product1.setPrice(10.0f);

        Product product2 = new Product();
        product2.setId(2);
        product2.setTitle("Product 2");
        product2.setPrice(15.0f);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCustomer(customer);
        orderDetail.setProducts(products);
        when(orderDetailRepository.save(orderDetail)).thenReturn(orderDetail);
        OrderDetail createdOrderDetail = orderDetailService.createOrder(orderDetail);
        assertThat(createdOrderDetail).isNotNull();
        assertThat(createdOrderDetail.getCustomer().getId()).isEqualTo(1L);
        assertThat(createdOrderDetail.getProducts()).hasSize(2);
        assertThat(createdOrderDetail.getProducts().get(0).getId()).isEqualTo(1L);
        assertThat(createdOrderDetail.getProducts().get(1).getId()).isEqualTo(2L);
    }
}
