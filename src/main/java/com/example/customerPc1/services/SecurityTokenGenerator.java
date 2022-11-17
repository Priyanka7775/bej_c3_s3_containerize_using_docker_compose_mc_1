package com.example.customerPc1.services;

import com.example.customerPc1.domain.Customer;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(Customer customer);
}
