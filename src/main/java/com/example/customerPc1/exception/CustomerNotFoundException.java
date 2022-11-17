package com.example.customerPc1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "Customer Not Found")
public class CustomerNotFoundException extends Exception{
}
