package com.emart.persistence.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@AllArgsConstructor
@Builder
public class OrderDetailDTO {
	private Long id;

	private ProductDTO product;

	private BigDecimal sellingPrice;

	private BigDecimal currentProductPrice;

	private Integer quantity;

	private OrderDTO order;
}
