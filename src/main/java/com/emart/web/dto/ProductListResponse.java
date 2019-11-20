package com.emart.web.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Okafor 2019-11-20
 */
@Getter
@Setter
public class ProductListResponse implements Serializable {

	private List<ProductDTO> products;

	private int limit;

	private long size;

	private int start;
}
