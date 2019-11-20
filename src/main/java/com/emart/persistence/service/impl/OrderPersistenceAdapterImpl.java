package com.emart.persistence.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emart.exceptions.NotFoundException;
import com.emart.persistence.entity.Order;
import com.emart.persistence.repository.OrderRepository;
import com.emart.persistence.service.OrderPersistenceAdapter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Service
public class OrderPersistenceAdapterImpl implements OrderPersistenceAdapter {

	private OrderRepository orderRepository;

	public OrderPersistenceAdapterImpl(final OrderRepository orderRepository) {
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

	@Override
	public List<Order> fetchOrdersByDate(final Date from, final Date to) {
		return orderRepository.getAllByDateCreatedBetween(new Timestamp(from.getTime()), new Timestamp(to.getTime()));
	}


}
