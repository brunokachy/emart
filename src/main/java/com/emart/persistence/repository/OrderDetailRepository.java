package com.emart.persistence.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.emart.persistence.entity.Order;
import com.emart.persistence.entity.OrderDetail;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Repository
public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Long> {

	List<OrderDetail> getAllByOrder(Order order);
}
