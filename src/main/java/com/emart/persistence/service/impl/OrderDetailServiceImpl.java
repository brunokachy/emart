package com.emart.persistence.service.impl;

import com.emart.exceptions.NotFoundException;
import com.emart.persistence.entity.OrderDetail;
import com.emart.persistence.repository.OrderDetailRepository;
import com.emart.persistence.service.OrderDetailService;

/**
 * @author Bruno Okafor 2019-11-19
 */
public class OrderDetailServiceImpl implements OrderDetailService {
	private OrderDetailRepository orderDetailRepository;

	public OrderDetailServiceImpl(final OrderDetailRepository orderDetailRepository) {
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
}
