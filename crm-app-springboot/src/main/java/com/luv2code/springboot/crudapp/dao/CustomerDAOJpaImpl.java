package com.luv2code.springboot.crudapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.crudapp.entity.Customer;

@Repository
public class CustomerDAOJpaImpl implements CustomerDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Customer> getCustomers() {
		Query query = entityManager.createQuery("from Customer order by lastName",Customer.class);
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer cust) {
		Customer temp = entityManager.merge(cust);
		
		//update with id from db...so we can get generated id
		cust.setId(temp.getId());
	}

	@Override
	public Customer getCustomer(int id) {
		
		return entityManager.find(Customer.class,id);
	}

	@Override
	public void deleteCustomer(int id) {
		Query q=entityManager.createQuery("delete from Customer where id=:customerId");
		q.setParameter("customerId",id);
		q.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		Query theQuery = null;
	    
	    // only search by name if theSearchName is not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) {
	
	    // search for firstName or lastName ... case insensitive
	    theQuery =entityManager.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	    theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =entityManager.createQuery("from Customer", Customer.class);            
		}
	
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
	        
		// return the results        
		return customers;
	}
}
