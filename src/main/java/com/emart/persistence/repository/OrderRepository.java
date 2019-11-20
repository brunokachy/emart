package com.emart.persistence.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.emart.persistence.entity.Order;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	List<Order> getAllByDateCreatedBetween(Timestamp start, Timestamp stop);

	Optional<Order> getOrderByOrderId(String orderId);
}
