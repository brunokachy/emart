package com.emart.web.dto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bruno Okafor 2019-11-21
 */
@Getter
@Setter
@ToString
public class CreateProductRequest implements Serializable {

	private String productName;

	private Double productPrice;

	private String productDescription;
}
