package com.emart.web.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Setter
public class CustomerDTO implements Serializable {

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;
}
