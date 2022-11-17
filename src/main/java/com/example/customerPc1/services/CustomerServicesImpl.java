package com.example.customerPc1.services;

import com.example.customerPc1.domain.Customer;
import com.example.customerPc1.exception.CustomerAlreadyFoundException;
import com.example.customerPc1.exception.CustomerNotFoundException;
import com.example.customerPc1.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServicesImpl implements CustomerServices{

    private CustomerRepository customerRepository;

    public CustomerServicesImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    @Override
    public Customer addCustomer(Customer customer) throws CustomerAlreadyFoundException {
        if(customerRepository.findById(customer.getCustomerId()).isPresent()){
            throw new CustomerAlreadyFoundException();
        }
        return customerRepository.save(customer);
    }


    @Override
    public Customer getAllByCustomerNameAndCustomerPassword(String customerName, String customerPassword) throws CustomerNotFoundException {
        Customer customer=customerRepository.findByCustomerNameAndCustomerPassword(customerName,customerPassword);
        if (customer==null){
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {
        boolean result=false;
        if (customerRepository.findById(customerId).isEmpty()){
            throw new CustomerNotFoundException();
        }else{
            customerRepository.deleteById(customerId);
            return true;
        }
    }
}
