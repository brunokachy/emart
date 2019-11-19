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

import lombok.Builder;
import lombok.Getter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Builder
@Entity
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, updatable = false)
	private String productName;

	@Column(nullable = false, precision = 36, scale = 16)
	private BigDecimal productPrice;

	private String productDescription;

	private Timestamp dateCreated;

	private Timestamp lastUpdated;

	@PreUpdate
	@PrePersist
	public void updateTimestamp() {
		lastUpdated = new Timestamp(System.currentTimeMillis());
		if (dateCreated == null) {
			dateCreated = new Timestamp(System.currentTimeMillis());
		}
	}

}
