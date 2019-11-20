package com.emart.service;

import com.emart.web.dto.ProductDTO;
import com.emart.web.dto.ProductListResponse;

/**
 * @author Bruno Okafor 2019-11-20
 */
public interface ProductService {

	ProductDTO createProduct(ProductDTO productDTO);

	ProductDTO updateProduct(ProductDTO productDTO);

	ProductListResponse fetchProducts(int startIndex, int size);
}
