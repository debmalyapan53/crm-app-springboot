package com.luv2code.springboot.crudapp.dao;

import java.util.*;
import com.luv2code.springboot.crudapp.entity.*;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer cust);
	
	public Customer getCustomer(int id);

	public void deleteCustomer(int id);
	
	public List<Customer> searchCustomers(String theSearchName);
}
