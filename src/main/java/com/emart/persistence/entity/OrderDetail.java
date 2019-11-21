package com.emart.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class OrderDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Product product;

	@ManyToOne
	private Order order;

	@Column(nullable = false, precision = 36, scale = 2)
	private BigDecimal sellingPrice;

	@Column(nullable = false)
	private Integer quantity;
}
