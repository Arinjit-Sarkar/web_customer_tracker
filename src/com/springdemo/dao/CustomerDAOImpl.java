package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//Inject session factory with Dependency Injection
	@Autowired
	private SessionFactory sessionfactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		Session currentSession=sessionfactory.getCurrentSession();
		
		Query<Customer> theQuery=currentSession.createQuery("from Customer order by lastName",Customer.class);
		
		List<Customer> customers= theQuery.getResultList();
		
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session currentSession=sessionfactory.getCurrentSession();
		//save if new record /update if existing  (use pk to check in background)
		currentSession.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int id) {
		
		Session currentSession= sessionfactory.getCurrentSession();
		
		Customer customer=currentSession.get(Customer.class,id);
		
		return customer;
		
	}

	@Override
	public void deleteCustomer(int customerId) {
		
		Session currentSession=sessionfactory.getCurrentSession();
		
		Query<Customer> theQuery=currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", customerId);
		theQuery.executeUpdate();
		
	}

}
