package com.emart.web.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Okafor 2019-11-20
 */
@Getter
@Setter
public class ProductRequest implements Serializable {

	private Long id;

	private String productName;

	private Double productPrice;

	private String productDescription;

	private Integer quantity;
}
