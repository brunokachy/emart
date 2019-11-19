package com.emart.persistence.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@AllArgsConstructor
@Builder
public class ProductDTO {

	private Long id;

	private String productName;

	private BigDecimal productPrice;

	private String productDescription;

	private Timestamp dateCreated;

	private Timestamp lastUpdated;
}
