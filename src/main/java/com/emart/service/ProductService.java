package com.emart.service;

import com.emart.web.dto.request.CreateProductRequest;
import com.emart.web.dto.request.UpdateProductRequest;
import com.emart.web.dto.response.ProductListResponse;
import com.emart.web.dto.response.ProductResponse;

/**
 * @author Bruno Okafor 2019-11-20
 */
public interface ProductService {

	ProductResponse createProduct(CreateProductRequest product);

	ProductResponse updateProduct(UpdateProductRequest product);

	ProductListResponse fetchProducts(int startIndex, int size);
}
