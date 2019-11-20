package com.emart.persistence.mappers;

import com.emart.persistence.dto.CustomerDTO;
import com.emart.persistence.entity.Customer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author Bruno Okafor 2019-11-19
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerMapper {

	static Customer toEntity(CustomerDTO customerDTO) {
		return Customer.builder()
				.email(customerDTO.getEmail())
				.firstName(customerDTO.getFirstName())
				.lastName(customerDTO.getLastName())
				.phoneNumber(customerDTO.getPhoneNumber())
				.build();
	}

	static CustomerDTO toDomain(Customer customer) {
		return CustomerDTO.builder()
				.id(customer.getId())
				.email(customer.getEmail())
				.firstName(customer.getFirstName())
				.lastName(customer.getLastName())
				.phoneNumber(customer.getPhoneNumber())
				.build();
	}
}
