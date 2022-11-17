package com.example.customerPc1.repository;

import com.example.customerPc1.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByCustomerNameAndCustomerPassword(String customerName, String customerPassword);
}

