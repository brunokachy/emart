package com.emart.service;

import java.util.Date;
import java.util.List;

import com.emart.web.dto.OrderDTO;

/**
 * @author Bruno Okafor 2019-11-20
 */
public interface OrderService {

	OrderDTO createOrder(OrderDTO orderRequest);

	List<OrderDTO> fetchOrders(Date from, Date to);
}
