package com.example.tripease.repository;

import com.example.tripease.Enum.Gender;
import com.example.tripease.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByGender(Gender gender);
    List<Customer> findByGenderAndAge(Gender gender, int age);

    // HQL (Hibernate Query Language)
    @Query("select c from Customer c where c.gender = :gender and c.age > :age")
    List<Customer> findByGenderAndAgeGreaterThan(Gender gender, int age);

    // SQL (Structured Query Language) by using "nativeQuery = true"
    // Can't use ENUMs (Make them String 1st)
    @Query(value = "select * from Customer where gender = :gender and age < :age", nativeQuery = true)
    List<Customer> findByGenderAndAgeLowerThan(String gender, int age);
}
