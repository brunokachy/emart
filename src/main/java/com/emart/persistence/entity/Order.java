package com.emart.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Bruno Okafor 2019-11-19
 */

@Getter
@Builder
@Entity
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private Customer customer;

	@Column(nullable = false, precision = 36, scale = 16)
	private BigDecimal totalOrderValue;

	@Column(nullable = false)
	private Timestamp dateCreated;

	@PreUpdate
	@PrePersist
	public void createTimestamp() {
		dateCreated = new Timestamp(System.currentTimeMillis());
	}
}
