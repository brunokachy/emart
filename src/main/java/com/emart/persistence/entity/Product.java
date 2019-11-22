package com.emart.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Setter
@Entity
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false, precision = 36, scale = 2)
	private BigDecimal productPrice;

	private String productDescription;

	private Timestamp dateCreated;

	private Timestamp lastUpdated;

	@Column(nullable = false, unique = true)
	private String productId;

	@PreUpdate
	@PrePersist
	public void updateTimestamp() {
		lastUpdated = new Timestamp(System.currentTimeMillis());
		if (dateCreated == null) {
			dateCreated = new Timestamp(System.currentTimeMillis());
		}
	}

}
