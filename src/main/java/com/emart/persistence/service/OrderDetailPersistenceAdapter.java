package com.emart.persistence.service;

import java.util.List;

import com.emart.persistence.entity.Order;
import com.emart.persistence.entity.OrderDetail;

/**
 * @author Bruno Okafor 2019-11-19
 */
public interface OrderDetailPersistenceAdapter extends CrudService<OrderDetail> {

	List<OrderDetail> fetchOrderDetailsByOrder(Order order);
}
