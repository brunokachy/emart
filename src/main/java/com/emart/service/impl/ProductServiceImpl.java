package com.emart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emart.persistence.dto.ProductDTO;
import com.emart.service.ProductService;
import com.emart.web.pojo.ProductRequest;

/**
 * @author Bruno Okafor 2019-11-20
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Override
	public ProductDTO createProduct(final ProductRequest productRequest) {
		return null;
	}

	@Override
	public ProductDTO updateProduct(final ProductRequest productRequest) {
		return null;
	}

	@Override
	public List<ProductDTO> fetchProducts() {
		return null;
	}
}
