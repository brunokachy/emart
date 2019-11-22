package com.emart.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.emart.web.dto.request.OrderDetailRequest;
import com.emart.web.dto.request.OrderRequest;
import com.emart.web.dto.response.OrderDetailResponse;
import com.emart.web.dto.response.OrderResponse;

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
	public OrderResponse createOrder(final OrderRequest orderRequest) {
		validateOrder(orderRequest);

		Customer customer = getCustomer(orderRequest.getCustomer());
		String orderId = generateUniqueOrderId();

		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderId(orderId);
		order.setTotalOrderValue(calculateTotalOrderValue(orderRequest.getOrderDetails()));
		orderPersistenceAdapter.saveRecord(order);

		List<OrderDetail> orderDetails = new ArrayList<>();

		for (OrderDetailRequest orderDetailRequest : orderRequest.getOrderDetails()) {
			Optional<Product> optionalProduct = productPersistenceAdapter.getProductByProductId(orderDetailRequest.getProductId());
			if (!optionalProduct.isPresent()) {
				throw new BadRequestException("Product does not exist");
			}
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProduct(optionalProduct.get());
			orderDetail.setQuantity(orderDetailRequest.getQuantity() <= 0 ? 1 : orderDetailRequest.getQuantity());
			orderDetail.setSellingPrice(optionalProduct.get().getProductPrice());
			orderDetail.setOrder(order);

			orderDetailPersistenceAdapter.saveRecord(orderDetail);
			orderDetails.add(orderDetail);
		}

		return buildOrderResponse(order, orderDetails);
	}

	@Override
	public List<OrderResponse> fetchOrders(final Date from, final Date to) {
		if (StringUtils.isEmpty(from)) {
			throw new BadRequestException("Missing required detail: Start date.");
		}

		if (StringUtils.isEmpty(to)) {
			throw new BadRequestException("Missing required detail: End date.");
		}

		List<Order> orders = orderPersistenceAdapter.fetchOrdersByDate(from, to);
		List<OrderResponse> orderList = new ArrayList<>();

		for (Order order : orders) {
			List<OrderDetail> orderDetails = orderDetailPersistenceAdapter.fetchOrderDetailsByOrder(order);
			orderList.add(buildOrderResponse(order, orderDetails));
		}

		return orderList;
	}

	private BigDecimal calculateTotalOrderValue(final List<OrderDetailRequest> orderDetails) {
		Double totalOrderValue = 0.0;

		for (OrderDetailRequest orderDetailRequest : orderDetails) {
			Optional<Product> optionalProduct = productPersistenceAdapter.getProductByProductId(orderDetailRequest.getProductId());

			if (!optionalProduct.isPresent()) {
				throw new BadRequestException("Product does not exist");
			}

			Double productOrderValue = optionalProduct.get().getProductPrice().doubleValue() * orderDetailRequest.getQuantity().doubleValue();
			totalOrderValue += productOrderValue;
		}
		return BigDecimal.valueOf(totalOrderValue);
	}

	private String generateUniqueOrderId() {
		String orderId = RandomStringUtils.randomAlphabetic(6).toUpperCase();
		Optional<Order> optionalOrder = orderPersistenceAdapter.getOrderByOrderId(orderId);

		while (optionalOrder.isPresent()) {
			orderId = RandomStringUtils.randomAlphabetic(6).toUpperCase();
			optionalOrder = orderPersistenceAdapter.getOrderByOrderId(orderId);
		}

		return orderId;
	}

	private Customer getCustomer(final CustomerDTO customerDTO) {
		Optional<Customer> optionalCustomer = customerPersistenceAdapter.getCustomerByEmail(customerDTO.getEmail());

		if (!optionalCustomer.isPresent()) {
			Customer customer = new Customer();
			customer.setEmail(customerDTO.getEmail());
			customer.setFirstName(customerDTO.getFirstName());
			customer.setLastName(customerDTO.getLastName());
			customer.setPhoneNumber(customerDTO.getPhoneNumber());

			return customerPersistenceAdapter.saveRecord(customer);
		}
		return optionalCustomer.get();
	}

	private CustomerDTO customerToCustomerDTO(final Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setPhoneNumber(customer.getPhoneNumber());

		return customerDTO;
	}

	private OrderResponse buildOrderResponse(final Order order, final List<OrderDetail> orderDetails) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setCustomer(customerToCustomerDTO(order.getCustomer()));
		orderResponse.setDateCreated(order.getDateCreated() != null ? new Date(order.getDateCreated().getTime()) : new Date());
		orderResponse.setOrderDetails(buildOrderDetailResponses(orderDetails));
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setTotalOrderValue(order.getTotalOrderValue().doubleValue());

		return orderResponse;
	}

	private List<OrderDetailResponse> buildOrderDetailResponses(final List<OrderDetail> orderDetails) {
		List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();

		for (int i = 0, orderDetailsSize = orderDetails.size(); i < orderDetailsSize; i++) {
			OrderDetail orderDetail = orderDetails.get(i);
			Product product = orderDetail.getProduct();
			OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
			orderDetailResponse.setProductDescription(product.getProductDescription());
			orderDetailResponse.setProductName(product.getProductName());
			orderDetailResponse.setQuantity(orderDetail.getQuantity());
			orderDetailResponse.setSellingPrice(product.getProductPrice().doubleValue());

			orderDetailResponses.add(orderDetailResponse);
		}

		return orderDetailResponses;
	}

	private void validateOrder(final OrderRequest orderRequest) {

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
