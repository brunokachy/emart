package com.emart.service;

import java.util.Date;
import java.util.List;

import com.emart.web.dto.request.OrderRequest;
import com.emart.web.dto.response.OrderResponse;

/**
 * @author Bruno Okafor 2019-11-20
 */
public interface OrderService {

	OrderResponse createOrder(OrderRequest orderRequest);

	List<OrderResponse> fetchOrders(Date from, Date to);
}
