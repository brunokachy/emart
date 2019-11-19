package com.emart.persistence.service;

import org.springframework.data.domain.Page;

import com.emart.persistence.entity.Product;

/**
 * @author Bruno Okafor 2019-11-19
 */
public interface ProductService extends CrudService<Product> {
	Page<Product> getProducts(int startIndex, int size);

}
