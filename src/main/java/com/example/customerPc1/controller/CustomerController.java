package com.example.customerPc1.controller;

import com.example.customerPc1.domain.Customer;
import com.example.customerPc1.exception.CustomerNotFoundException;
import com.example.customerPc1.services.CustomerServices;
import com.example.customerPc1.services.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    private CustomerServices customerServices;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public CustomerController(CustomerServices customerServices,SecurityTokenGenerator securityTokenGenerator){
        this.customerServices=customerServices;
        this.securityTokenGenerator=securityTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer customer)throws CustomerNotFoundException {
        Map<String, String> map = null;
        try {
            Customer customer1 = customerServices.getAllByCustomerNameAndCustomerPassword(customer.getCustomerName(), customer.getCustomerPassword());
            if (customer1.getCustomerName().equals(customer.getCustomerName())) {
                map = securityTokenGenerator.generateToken(customer);
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return new ResponseEntity<>("Try after sometimes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?>saveCustomerData(@RequestBody Customer customer) throws Exception{
        Customer createdCustomer=customerServices.addCustomer(customer);
        return new ResponseEntity<>("Customer Created",HttpStatus.CREATED);
    }

    @GetMapping("/customerDetails/v1/fetchcustomer")
    public ResponseEntity<?> getAllCustomer(){
        List<Customer> customerList=customerServices.getAllCustomer();
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @DeleteMapping("/customerDetails/v1/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) throws CustomerNotFoundException{
        ResponseEntity responseEntity=null;
        try{
            customerServices.deleteCustomer(customerId);
            responseEntity=new ResponseEntity("Succesfully Deleted",HttpStatus.OK);
        }catch (CustomerNotFoundException customerNotFoundException){
            throw new CustomerNotFoundException();
        }catch (Exception exception){
            responseEntity=new ResponseEntity(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
