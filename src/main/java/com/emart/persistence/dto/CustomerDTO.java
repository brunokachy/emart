package com.emart.persistence.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@AllArgsConstructor
@Builder
public class CustomerDTO implements Serializable {

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;
}
