package com.emart.persistence.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emart.exceptions.NotFoundException;
import com.emart.persistence.entity.Product;
import com.emart.persistence.repository.ProductRepository;
import com.emart.persistence.service.ProductPersistenceAdapter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Service
public class ProductPersistenceAdapterImpl implements ProductPersistenceAdapter {
	private ProductRepository productRepository;

	public ProductPersistenceAdapterImpl(final ProductRepository productRepository) {
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
	public Page<Product> getProducts(int startIndex, int limit) {
		Pageable pageable = PageRequest.of(startIndex, limit);
		return productRepository.getProducts(pageable);
	}
}
