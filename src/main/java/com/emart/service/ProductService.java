package com.emart.service;

import java.util.List;

import com.emart.persistence.dto.ProductDTO;
import com.emart.web.pojo.ProductRequest;

/**
 * @author Bruno Okafor 2019-11-20
 */
public interface ProductService {

	ProductDTO createProduct(ProductRequest productRequest);

	ProductDTO updateProduct(ProductRequest productRequest);

	List<ProductDTO> fetchProducts();
}
