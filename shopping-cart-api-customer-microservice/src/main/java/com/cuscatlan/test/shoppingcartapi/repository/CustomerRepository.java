package com.cuscatlan.test.shoppingcartapi.repository;

import com.cuscatlan.test.shoppingcartapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
