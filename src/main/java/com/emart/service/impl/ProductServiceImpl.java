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
import com.emart.web.dto.ProductDTO;
import com.emart.web.dto.ProductListResponse;

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
	public ProductDTO createProduct(final ProductDTO productDTO) {

		if (StringUtils.isEmpty(productDTO.getProductName())) {
			throw new BadRequestException("Missing required detail: Product name.");
		}

		if (StringUtils.isEmpty(productDTO.getProductPrice())) {
			throw new BadRequestException("Missing required detail: Product price.");
		}

		Product product = new Product();
		product.setProductDescription(productDTO.getProductDescription());
		product.setProductName(productDTO.getProductName());
		product.setProductPrice(BigDecimal.valueOf(productDTO.getProductPrice()));

		product = productPersistenceAdapter.saveRecord(product);

		productDTO.setId(product.getId());

		return productDTO;
	}

	@Override
	public ProductDTO updateProduct(final ProductDTO productDTO) {

		if (StringUtils.isEmpty(productDTO.getProductPrice())) {
			throw new BadRequestException("Missing required detail: Product price.");
		}

		if (StringUtils.isEmpty(productDTO.getId())) {
			throw new BadRequestException("Missing required detail: Product id.");
		}

		Product product = productPersistenceAdapter.getRecordById(productDTO.getId());
		product.setProductDescription(productDTO.getProductDescription());
		product.setProductName(productDTO.getProductName());
		product.setProductPrice(BigDecimal.valueOf(productDTO.getProductPrice()));

		productPersistenceAdapter.saveRecord(product);

		return productDTO;
	}

	@Override
	public ProductListResponse fetchProducts(int startIndex, int limit) {
		Page<Product> productPage = productPersistenceAdapter.getProducts(startIndex, limit);
		List<ProductDTO> productDTOS = new ArrayList<>();

		productPage.forEach(product -> {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setProductDescription(product.getProductDescription());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice().doubleValue());
			productDTOS.add(productDTO);
		});

		ProductListResponse productListResponse = new ProductListResponse();
		productListResponse.setLimit(limit);
		productListResponse.setSize(productPage.getTotalElements());
		productListResponse.setStart(startIndex);
		productListResponse.setProducts(productDTOS);
		return productListResponse;
	}
}
