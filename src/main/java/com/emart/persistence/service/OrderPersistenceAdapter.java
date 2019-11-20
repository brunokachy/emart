package com.emart.persistence.service;

import java.util.Date;
import java.util.List;

import com.emart.persistence.entity.Order;

/**
 * @author Bruno Okafor 2019-11-19
 */
public interface OrderPersistenceAdapter extends CrudService<Order> {

	List<Order> fetchOrdersByDate(Date from, Date to);
}
