package com.emart.persistence.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emart.persistence.entity.Product;

/**
 * @author Bruno Okafor 2019-11-19
 */
public interface ProductPersistenceAdapter extends CrudService<Product> {

	Page<Product> getProducts(int startIndex, int limit);

	Optional<Product> getProductByProductId(String productId);
}
