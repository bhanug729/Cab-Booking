package com.example.tripease.service;

import com.example.tripease.Enum.Gender;
import com.example.tripease.dto.request.CustomerRequest;
import com.example.tripease.dto.response.CustomerResponse;
import com.example.tripease.exception.CustomerNotFoundException;
import com.example.tripease.model.Customer;
import com.example.tripease.repository.CustomerRepository;
import com.example.tripease.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        // Request DTO --> Entity
        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);

        // Save the entity to DB
        Customer savedCustomer = customerRepository.save(customer);

        // Entity to Response DTO
        return CustomerTransformer.customertoCustomerResponse(savedCustomer);
    }

    public CustomerResponse getCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if(optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer Not Found!!");
        }
        Customer customer = optionalCustomer.get();

        return CustomerTransformer.customertoCustomerResponse(customer);
    }

    public List<CustomerResponse> getAllByGender(Gender gender) {
        List<Customer> customers = customerRepository.findByGender(gender);

        // Entity to Response DTO
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer:customers) {
            customerResponses.add(CustomerTransformer.customertoCustomerResponse(customer));
        }
        return customerResponses;
    }

    public List<CustomerResponse> getAllByGenderAndAge(Gender gender, int age) {
        List<Customer> customers = customerRepository.findByGenderAndAge(gender, age);

        // Entity to Response DTO
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer:customers) {
            customerResponses.add(CustomerTransformer.customertoCustomerResponse(customer));
        }
        return customerResponses;
    }

    public List<CustomerResponse> getAllByGenderAndAgeGreaterThan(Gender gender, int age) {
        List<Customer> customers = customerRepository.findByGenderAndAgeGreaterThan(gender, age);

        // Entity to Response DTO
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer:customers) {
            customerResponses.add(CustomerTransformer.customertoCustomerResponse(customer));
        }
        return customerResponses;
    }

    public List<CustomerResponse> getAllByGenderAndAgeLowerThan(String gender, int age) {
        List<Customer> customers = customerRepository.findByGenderAndAgeLowerThan(gender, age);

        // Entity to Response DTO
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer:customers) {
            customerResponses.add(CustomerTransformer.customertoCustomerResponse(customer));
        }
        return customerResponses;
    }
}
