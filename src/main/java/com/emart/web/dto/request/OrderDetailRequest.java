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
public class OrderDetailRequest implements Serializable {
	private String productId;

	private Integer quantity;
}
