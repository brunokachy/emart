package com.emart.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Setter
public class OrderDetailDTO {
	private ProductDTO product;

	private Double sellingPrice;

	private Integer quantity;
}
