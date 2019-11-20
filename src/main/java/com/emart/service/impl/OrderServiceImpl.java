package com.emart.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emart.exceptions.BadRequestException;
import com.emart.persistence.entity.Customer;
import com.emart.persistence.entity.Order;
import com.emart.persistence.entity.OrderDetail;
import com.emart.persistence.entity.Product;
import com.emart.persistence.service.CustomerPersistenceAdapter;
import com.emart.persistence.service.OrderDetailPersistenceAdapter;
import com.emart.persistence.service.OrderPersistenceAdapter;
import com.emart.persistence.service.ProductPersistenceAdapter;
import com.emart.service.OrderService;
import com.emart.web.dto.CustomerDTO;
import com.emart.web.dto.OrderDTO;
import com.emart.web.dto.OrderDetailDTO;
import com.emart.web.dto.ProductDTO;

/**
 * @author Bruno Okafor 2019-11-20
 */
@Service
public class OrderServiceImpl implements OrderService {

	private CustomerPersistenceAdapter customerPersistenceAdapter;
	private OrderPersistenceAdapter orderPersistenceAdapter;
	private OrderDetailPersistenceAdapter orderDetailPersistenceAdapter;
	private ProductPersistenceAdapter productPersistenceAdapter;

	public OrderServiceImpl(
			final CustomerPersistenceAdapter customerPersistenceAdapter,
			final OrderPersistenceAdapter orderPersistenceAdapter,
			final OrderDetailPersistenceAdapter orderDetailPersistenceAdapter,
			final ProductPersistenceAdapter productPersistenceAdapter) {
		this.customerPersistenceAdapter = customerPersistenceAdapter;
		this.orderPersistenceAdapter = orderPersistenceAdapter;
		this.orderDetailPersistenceAdapter = orderDetailPersistenceAdapter;
		this.productPersistenceAdapter = productPersistenceAdapter;
	}

	@Override
	@Transactional
	public OrderDTO createOrder(final OrderDTO orderRequest) {
		validateOrder(orderRequest);

		Double totalOrderValue = 0.0;

		Customer customer = Customer.builder()
				.phoneNumber(orderRequest.getCustomer().getPhoneNumber())
				.lastName(orderRequest.getCustomer().getLastName())
				.firstName(orderRequest.getCustomer().getFirstName())
				.email(orderRequest.getCustomer().getEmail())
				.build();
		customer = customerPersistenceAdapter.saveRecord(customer);

		Order order = Order.builder()
				.totalOrderValue(BigDecimal.valueOf(totalOrderValue))
				.customer(customer)
				.orderId(RandomStringUtils.randomAlphabetic(6).toUpperCase())
				.build();
		order = orderPersistenceAdapter.saveRecord(order);

		final List<OrderDetailDTO> orderDetails = orderRequest.getOrderDetails();
		for (int i = 0, orderDetailsSize = orderDetails.size(); i < orderDetailsSize; i++) {
			final OrderDetailDTO orderDetailDTO = orderDetails.get(i);
			Product product = productPersistenceAdapter.getRecordById(orderDetailDTO.getProduct().getId());
			OrderDetail orderDetail = OrderDetail.builder()
					.sellingPrice(product.getProductPrice())
					.quantity(orderDetailDTO.getQuantity())
					.product(product)
					.order(order)
					.build();
			orderDetailPersistenceAdapter.saveRecord(orderDetail);

			Double productOrderValue = orderDetail.getSellingPrice().doubleValue() * orderDetail.getQuantity().doubleValue();
			totalOrderValue += productOrderValue;
		}

		order.setTotalOrderValue(BigDecimal.valueOf(totalOrderValue));
		orderPersistenceAdapter.saveRecord(order);

		orderRequest.setOrderId(order.getOrderId());
		orderRequest.setTotalOrderValue(totalOrderValue);

		return orderRequest;
	}

	@Override
	public List<OrderDTO> fetchOrders(final Date from, final Date to) {
		if (StringUtils.isEmpty(from)) {
			throw new BadRequestException("Missing required detail: Start date.");
		}

		if (StringUtils.isEmpty(to)) {
			throw new BadRequestException("Missing required detail: End date.");
		}

		List<Order> orders = orderPersistenceAdapter.fetchOrdersByDate(from, to);

		List<OrderDTO> orderList = new ArrayList<>();

		for (Order order : orders) {
			List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
			List<OrderDetail> orderDetails = orderDetailPersistenceAdapter.fetchOrderDetailsByOrder(order);

			for (OrderDetail orderDetail : orderDetails) {
				OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
				orderDetailDTO.setProduct(from(orderDetail.getProduct()));
				orderDetailDTO.setQuantity(orderDetail.getQuantity());
				orderDetailDTO.setSellingPrice(orderDetail.getSellingPrice().doubleValue());

				orderDetailDTOS.add(orderDetailDTO);
			}

			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setTotalOrderValue(order.getTotalOrderValue().doubleValue());
			orderDTO.setOrderId(order.getOrderId());
			orderDTO.setDateCreated(new Date(order.getDateCreated().getTime()));
			orderDTO.setOrderDetails(orderDetailDTOS);
			orderDTO.setCustomer(from(order.getCustomer()));

			orderList.add(orderDTO);
		}

		return orderList;
	}

	private CustomerDTO from(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setPhoneNumber(customer.getPhoneNumber());

		return customerDTO;
	}

	private ProductDTO from(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductPrice(product.getProductPrice().doubleValue());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductDescription(product.getProductDescription());
		productDTO.setId(product.getId());

		return productDTO;
	}

	private void validateOrder(final OrderDTO orderRequest) {

		if (StringUtils.isEmpty(orderRequest)) {
			throw new BadRequestException("Missing required detail: Order Request Object.");
		}

		if (StringUtils.isEmpty(orderRequest.getCustomer()) || StringUtils.isEmpty(orderRequest.getCustomer().getEmail())) {
			throw new BadRequestException("Missing required detail: Customer email.");
		}

		if (orderRequest.getOrderDetails().isEmpty()) {
			throw new BadRequestException("Missing required detail: Order Details.");
		}

	}
}
