package com.emart.web.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Setter
public class OrderDetailDTO implements Serializable {
	private ProductDTO product;

	private Double sellingPrice;

	private Integer quantity;
}
