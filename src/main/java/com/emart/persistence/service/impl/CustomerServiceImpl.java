package com.emart.persistence.service.impl;

import org.springframework.stereotype.Service;

import com.emart.persistence.entity.Customer;
import com.emart.persistence.repository.CustomerRepository;
import com.emart.persistence.service.CustomerService;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	public CustomerServiceImpl(final CustomerRepository customerRepository) {
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
