package com.emart.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Builder
@Entity
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String firstName;

	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	private String phoneNumber;
}