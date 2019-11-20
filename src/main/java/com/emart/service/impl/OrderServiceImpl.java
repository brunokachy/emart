package com.emart.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emart.service.OrderService;
import com.emart.web.pojo.OrderRequest;
import com.emart.web.pojo.OrderResponse;

/**
 * @author Bruno Okafor 2019-11-20
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Override
	public OrderResponse createOrder(final OrderRequest orderRequest) {
		return null;
	}

	@Override
	public List<OrderResponse> fetchOrders(final Date from, final Date to) {
		return null;
	}
}
