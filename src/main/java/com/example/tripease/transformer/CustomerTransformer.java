package com.example.tripease.transformer;

import com.example.tripease.dto.request.CustomerRequest;
import com.example.tripease.dto.response.CustomerResponse;
import com.example.tripease.model.Customer;

public class CustomerTransformer {

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .emailId(customerRequest.getEmailId())
                .gender(customerRequest.getGender())
                .build();

//        Second Way (Don't Prefer)
//        Customer customer = new Customer();
//        customer.setName(customerRequest.getName());
//        customer.setAge(customerRequest.getAge());
//        customer.setEmailId(customerRequest.getEmailId());
//        customer.setGender(customerRequest.getGender());
//
//        return customer;
    }

    public static CustomerResponse customertoCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .emailId(customer.getEmailId())
                .build();
    }

}
