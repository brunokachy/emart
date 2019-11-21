package com.emart.web.dto.response;

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
public class OrderDetailResponse implements Serializable {

	private String productName;

	private String productDescription;

	private Double sellingPrice;

	private Integer quantity;
}
