package com.emart.persistence.service;

import java.util.Optional;

import com.emart.persistence.entity.Customer;

/**
 * @author Bruno Okafor 2019-11-19
 */
public interface CustomerPersistenceAdapter extends CrudService<Customer> {

	Optional<Customer> getCustomerByEmail(String email);
}
