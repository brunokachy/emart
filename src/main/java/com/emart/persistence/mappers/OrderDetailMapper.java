package com.emart.persistence.mappers;

import com.emart.persistence.dto.OrderDetailDTO;
import com.emart.persistence.entity.OrderDetail;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author Bruno Okafor 2019-11-19
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDetailMapper {

	static OrderDetail toEntity(OrderDetailDTO orderDetailDTO) {
		return OrderDetail.builder()
				.currentProductPrice(orderDetailDTO.getCurrentProductPrice())
				.discount(orderDetailDTO.getDiscount())
				.order(OrderMapper.toEntity(orderDetailDTO.getOrder()))
				.product(ProductMapper.toEntity(orderDetailDTO.getProduct()))
				.quantity(orderDetailDTO.getQuantity())
				.sellingPrice(orderDetailDTO.getSellingPrice())
				.build();
	}

	static OrderDetailDTO toDomain(OrderDetail orderDetail) {
		return OrderDetailDTO.builder()
				.currentProductPrice(orderDetail.getCurrentProductPrice())
				.discount(orderDetail.getDiscount())
				.id(orderDetail.getId())
				.order(OrderMapper.toDomain(orderDetail.getOrder()))
				.product(ProductMapper.toDomain(orderDetail.getProduct()))
				.quantity(orderDetail.getQuantity())
				.sellingPrice(orderDetail.getSellingPrice())
				.build();
	}
}
