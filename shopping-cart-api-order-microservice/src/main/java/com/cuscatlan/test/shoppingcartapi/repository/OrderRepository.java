package com.cuscatlan.test.shoppingcartapi.repository;

import com.cuscatlan.test.shoppingcartapi.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByCustomer_Id(Long customerId);
}
