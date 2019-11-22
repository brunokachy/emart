package com.emart.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emart.persistence.entity.Product;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	@Query(value = "select p from Product p")
	Page<Product> getProducts(Pageable pageable);

	Optional<Product> getByProductId(String productId);
}
