package com.emart.persistence.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emart.exceptions.NotFoundException;
import com.emart.persistence.entity.Order;
import com.emart.persistence.entity.OrderDetail;
import com.emart.persistence.repository.OrderDetailRepository;
import com.emart.persistence.service.OrderDetailPersistenceAdapter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Service
public class OrderDetailPersistenceAdapterImpl implements OrderDetailPersistenceAdapter {
	private OrderDetailRepository orderDetailRepository;

	public OrderDetailPersistenceAdapterImpl(final OrderDetailRepository orderDetailRepository) {
		this.orderDetailRepository = orderDetailRepository;
	}

	@Override
	public OrderDetail getRecordById(final Long id) {
		return orderDetailRepository.findById(id).orElseThrow(() -> new NotFoundException("Record not found: OrderDetail with Id: " + id));
	}

	@Override
	public OrderDetail saveRecord(final OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
	}

	@Override
	public List<OrderDetail> fetchOrderDetailsByOrder(final Order order) {
		return orderDetailRepository.getAllByOrder(order);
	}
}
