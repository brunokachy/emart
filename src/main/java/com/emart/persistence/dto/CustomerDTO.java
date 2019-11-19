package com.emart.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@AllArgsConstructor
@Builder
public class CustomerDTO {

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;
}
