package com.emart.persistence.mappers;

import com.emart.persistence.dto.OrderDTO;
import com.emart.persistence.entity.Order;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author Bruno Okafor 2019-11-19
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {

	static Order toEntity(OrderDTO orderDTO) {
		return Order.builder()
				.customer(CustomerMapper.toEntity(orderDTO.getCustomer()))
				.dateCreated(orderDTO.getDateCreated())
				.totalOrderValue(orderDTO.getTotalOrderValue())
				.build();
	}

	static OrderDTO toDomain(Order order) {
		return OrderDTO.builder()
				.customer(CustomerMapper.toDomain(order.getCustomer()))
				.dateCreated(order.getDateCreated())
				.id(order.getId())
				.totalOrderValue(order.getTotalOrderValue())
				.build();
	}

}
