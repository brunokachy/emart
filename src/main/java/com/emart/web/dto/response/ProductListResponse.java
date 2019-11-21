package com.emart.web.dto.response;

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

	private List<ProductResponse> products;

	private int limit;

	private long size;

	private int start;
}
