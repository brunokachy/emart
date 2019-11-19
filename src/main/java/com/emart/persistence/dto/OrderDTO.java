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
public class OrderDTO {

	private Long id;

	private CustomerDTO customer;

	private BigDecimal totalOrderValue;

	private Timestamp dateCreated;

}
