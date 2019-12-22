package com.luv2code.springboot.crudapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.crudapp.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

}
