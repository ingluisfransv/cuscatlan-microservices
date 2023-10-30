package com.cuscatlan.test.shoppingcartapi.repository;

import com.cuscatlan.test.shoppingcartapi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
