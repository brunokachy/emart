package com.emart.web.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Setter
@ToString
public class ProductDTO implements Serializable {

	private Long id;

	private String productName;

	private Double productPrice;

	private String productDescription;

}
