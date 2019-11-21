package com.emart.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emart.exceptions.BadRequestException;
import com.emart.persistence.entity.Product;
import com.emart.persistence.service.ProductPersistenceAdapter;
import com.emart.service.ProductService;
import com.emart.web.dto.request.CreateProductRequest;
import com.emart.web.dto.request.UpdateProductRequest;
import com.emart.web.dto.response.ProductListResponse;
import com.emart.web.dto.response.ProductResponse;

/**
 * @author Bruno Okafor 2019-11-20
 */
@Service
public class ProductServiceImpl implements ProductService {

	private ProductPersistenceAdapter productPersistenceAdapter;

	public ProductServiceImpl(final ProductPersistenceAdapter productPersistenceAdapter) {
		this.productPersistenceAdapter = productPersistenceAdapter;
	}

	@Override
	public ProductResponse createProduct(final CreateProductRequest requestProductDTO) {

		if (StringUtils.isEmpty(requestProductDTO.getProductName())) {
			throw new BadRequestException("Missing required detail: Product name.");
		}

		if (StringUtils.isEmpty(requestProductDTO.getProductPrice())) {
			throw new BadRequestException("Missing required detail: Product price.");
		}

		Product product = new Product();
		product.setProductDescription(requestProductDTO.getProductDescription());
		product.setProductName(requestProductDTO.getProductName());
		product.setProductPrice(BigDecimal.valueOf(requestProductDTO.getProductPrice()));

		product = productPersistenceAdapter.saveRecord(product);

		return buildProductResponse(product);
	}

	@Override
	public ProductResponse updateProduct(final UpdateProductRequest productDTO) {

		if (StringUtils.isEmpty(productDTO.getProductPrice())) {
			throw new BadRequestException("Missing required detail: Product price.");
		}

		if (StringUtils.isEmpty(productDTO.getProductPrice())) {
			throw new BadRequestException("Missing required detail: Product name.");
		}

		if (StringUtils.isEmpty(productDTO.getId())) {
			throw new BadRequestException("Missing required detail: Product id.");
		}

		Product product = productPersistenceAdapter.getRecordById(productDTO.getId());
		product.setProductDescription(productDTO.getProductDescription());
		product.setProductName(productDTO.getProductName());
		product.setProductPrice(BigDecimal.valueOf(productDTO.getProductPrice()));

		productPersistenceAdapter.saveRecord(product);

		return buildProductResponse(product);
	}

	@Override
	public ProductListResponse fetchProducts(final int startIndex, final int limit) {
		Page<Product> productPage = productPersistenceAdapter.getProducts(startIndex, limit);
		List<ProductResponse> products = new ArrayList<>();

		productPage.forEach(product -> products.add(buildProductResponse(product)));

		ProductListResponse productListResponse = new ProductListResponse();
		productListResponse.setLimit(limit);
		productListResponse.setSize(productPage.getTotalElements());
		productListResponse.setStart(startIndex);
		productListResponse.setProducts(products);

		return productListResponse;
	}

	private ProductResponse buildProductResponse(final Product product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(product.getId());
		productResponse.setProductDescription(product.getProductDescription());
		productResponse.setProductName(product.getProductName());
		productResponse.setProductPrice(product.getProductPrice().doubleValue());

		return productResponse;
	}
}
