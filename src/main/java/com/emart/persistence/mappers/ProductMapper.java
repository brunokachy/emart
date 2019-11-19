package com.emart.persistence.mappers;

import com.emart.persistence.dto.ProductDTO;
import com.emart.persistence.entity.Product;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author Bruno Okafor 2019-11-19
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

	static Product toEntity(ProductDTO productDTO) {
		return Product.builder()
				.dateCreated(productDTO.getDateCreated())
				.id(productDTO.getId())
				.lastUpdated(productDTO.getLastUpdated())
				.productDescription(productDTO.getProductDescription())
				.productName(productDTO.getProductName())
				.productPrice(productDTO.getProductPrice())
				.build();
	}

	static ProductDTO toDomain(Product product) {
		return ProductDTO.builder()
				.dateCreated(product.getDateCreated())
				.id(product.getId())
				.lastUpdated(product.getLastUpdated())
				.productDescription(product.getProductDescription())
				.productName(product.getProductName())
				.productPrice(product.getProductPrice())
				.build();
	}
}
