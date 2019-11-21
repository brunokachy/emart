package com.emart.persistence.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emart.exceptions.NotFoundException;
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
		return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Record not found: Customer with Id: " + id));
	}

	@Override
	public Customer saveRecord(final Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Optional<Customer> getCustomerByEmail(final String email) {
		return customerRepository.findByEmail(email);
	}
}
