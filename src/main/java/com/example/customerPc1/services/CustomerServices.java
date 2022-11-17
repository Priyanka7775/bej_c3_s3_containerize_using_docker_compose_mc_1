package com.example.customerPc1.services;

import com.example.customerPc1.domain.Customer;
import com.example.customerPc1.exception.CustomerAlreadyFoundException;
import com.example.customerPc1.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerServices {
    public Customer addCustomer(Customer customer) throws CustomerAlreadyFoundException;
    public Customer getAllByCustomerNameAndCustomerPassword(String customerName,String customerPassword) throws CustomerNotFoundException;
    List<Customer> getAllCustomer();
    boolean deleteCustomer(int customerId) throws CustomerNotFoundException;
}
