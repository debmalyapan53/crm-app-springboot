package com.luv2code.springboot.crudapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.crudapp.dao.CustomerRepo;
import com.luv2code.springboot.crudapp.entity.Customer;

@Service
public class CustomerServiceRepo implements CustomerService {

	@Autowired
	@Qualifier("customerRepo")
	private CustomerRepo customerRepository;
	
	@Override
	public List<Customer> getCustomers() {
		
		return customerRepository.findAll();
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		customerRepository.save(theCustomer);

	}

	@Override
	public Customer getCustomer(int theId) {
		Optional<Customer> result = customerRepository.findById(theId);
		
		Customer temp = null;
		
		if(result.isPresent()) {
			temp = result.get();
		}
		else
			throw new RuntimeException("Didnt find employee id: "+theId);
		return temp;
	}

	@Override
	public void deleteCustomer(int theId) {
			customerRepository.deleteById(theId);

	}

}
