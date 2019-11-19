package com.emart.persistence.service.impl;

import com.emart.exceptions.NotFoundException;
import com.emart.persistence.entity.Order;
import com.emart.persistence.repository.OrderRepository;
import com.emart.persistence.service.OrderService;

/**
 * @author Bruno Okafor 2019-11-19
 */
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	public OrderServiceImpl(final OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order getRecordById(final Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Record not found: Order with Id: " + id));
	}

	@Override
	public Order saveRecord(final Order order) {
		return orderRepository.save(order);
	}
}
