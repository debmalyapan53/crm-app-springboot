package com.luv2code.springboot.crudapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.crudapp.entity.Customer;
import com.luv2code.springboot.crudapp.service.CustomerServiceRepo;

@RestController
@RequestMapping("/api")
class CustomerRestController {

	@Autowired
	@Qualifier("customerServiceRepo")
	private CustomerServiceRepo customerService;
	//private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer customer = customerService.getCustomer(customerId);
		/*if(customer==null)
			throw new CustomerNotFoundException("Customer id not found - "+customerId);*/
		
		return customer;
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		
		//We explicitly set id = 0 (means, null) coz in the backend, Hibernate will set it by calling save
		customer.setId(0);
		customerService.saveCustomer(customer);
		return customer;
	}

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		
		//Here id needs to be explicitly passed and Hibernate will set it by calling update
		customerService.saveCustomer(customer);
		return customer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		//check if exists
		/*Customer tempcustomer = customerService.getCustomer(customerId);
		if(tempcustomer==null)
			throw new CustomerNotFoundException("Customer id not found - "+customerId);*/
		
		customerService.deleteCustomer(customerId);
		
		return "Deleted customer id: " + customerId;
	}
	
	
	
}
