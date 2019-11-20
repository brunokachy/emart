package com.emart.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Setter
public class ProductDTO {

	private Long id;

	private String productName;

	private Double productPrice;

	private String productDescription;

}
