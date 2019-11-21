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
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bruno Okafor 2019-11-19
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "product_order")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private Customer customer;

	@Column(nullable = false, precision = 36, scale = 2)
	private BigDecimal totalOrderValue;

	@Column(nullable = false)
	private Timestamp dateCreated;

	@Column(nullable = false)
	private String orderId;

	@PreUpdate
	@PrePersist
	public void createTimestamp() {
		dateCreated = new Timestamp(System.currentTimeMillis());
	}
}
