package com.emart.web.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Okafor 2019-11-20
 */
@Getter
@Setter
public class OrderRequest implements Serializable {

	private String customerFirstName;

	private String customerLastName;

	private String customerEmail;

	private String customerPhoneNumber;

	private List<ProductRequest> productList;
}
