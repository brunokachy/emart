package com.emart.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Builder
@Entity
public class OrderDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToOne
	private Product product;

	@ManyToOne
	private Order order;

	@Column(nullable = false, precision = 36, scale = 16)
	private BigDecimal sellingPrice;

	@Column(nullable = false)
	private Integer quantity;
}
