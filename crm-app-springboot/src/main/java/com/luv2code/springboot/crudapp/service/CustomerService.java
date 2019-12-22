package com.luv2code.springboot.crudapp.service;

import java.util.List;

import com.luv2code.springboot.crudapp.entity.*;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
}
