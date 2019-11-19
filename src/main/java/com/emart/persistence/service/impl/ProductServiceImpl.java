package com.emart.persistence.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.emart.exceptions.NotFoundException;
import com.emart.persistence.entity.Product;
import com.emart.persistence.repository.ProductRepository;
import com.emart.persistence.service.ProductService;

/**
 * @author Bruno Okafor 2019-11-19
 */
public class ProductServiceImpl implements ProductService {
	private ProductRepository productRepository;

	public ProductServiceImpl(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product getRecordById(final Long id) {
		return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Record not found: Product with Id: " + id));
	}

	@Override
	public Product saveRecord(final Product product) {
		return productRepository.save(product);
	}

	@Override
	public Page<Product> getProducts(int startIndex, int size) {
		Pageable pageable = PageRequest.of(startIndex, size);
		return productRepository.getProducts(pageable);
	}
}
