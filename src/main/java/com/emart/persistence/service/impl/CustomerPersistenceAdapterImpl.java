package com.emart.persistence.service.impl;

import org.springframework.stereotype.Service;

import com.emart.persistence.entity.Customer;
import com.emart.persistence.repository.CustomerRepository;
import com.emart.persistence.service.CustomerPersistenceAdapter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Service
public class CustomerPersistenceAdapterImpl implements CustomerPersistenceAdapter {

	private CustomerRepository customerRepository;

	public CustomerPersistenceAdapterImpl(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer getRecordById(final Long id) {
		return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found: Customer with Id: " + id));
	}

	@Override
	public Customer saveRecord(final Customer customer) {
		return customerRepository.save(customer);
	}

}
