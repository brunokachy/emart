package com.emart.service.impl;


import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emart.exceptions.BadRequestException;
import com.emart.persistence.entity.Customer;
import com.emart.persistence.entity.Order;
import com.emart.persistence.entity.OrderDetail;
import com.emart.persistence.entity.Product;
import com.emart.persistence.service.CustomerPersistenceAdapter;
import com.emart.persistence.service.OrderDetailPersistenceAdapter;
import com.emart.persistence.service.OrderPersistenceAdapter;
import com.emart.persistence.service.ProductPersistenceAdapter;
import com.emart.web.dto.CustomerDTO;
import com.emart.web.dto.request.OrderDetailRequest;
import com.emart.web.dto.request.OrderRequest;
import com.emart.web.dto.response.OrderResponse;

/**
 * @author Bruno Okafor 2019-11-21
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceImplTest {

	@InjectMocks
	OrderServiceImpl orderService;
	@Mock
	private CustomerPersistenceAdapter customerPersistenceAdapter;
	@Mock
	private OrderPersistenceAdapter orderPersistenceAdapter;
	@Mock
	private OrderDetailPersistenceAdapter orderDetailPersistenceAdapter;
	@Mock
	private ProductPersistenceAdapter productPersistenceAdapter;

	@Test
	public void whenCreateOrderThenSuccess() {
		String orderId = "order_Id";
		String customerEmail = "customer_email";
		OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
		orderDetailRequest.setProductId("productId");
		orderDetailRequest.setQuantity(1);
		List<OrderDetailRequest> orderDetailRequests = Collections.singletonList(orderDetailRequest);

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setEmail(customerEmail);

		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setCustomer(customerDTO);
		orderRequest.setOrderDetails(orderDetailRequests);

		Product product = new Product();
		product.setProductId("product_Id");
		product.setProductName("product_name");
		product.setProductPrice(BigDecimal.TEN);

		Customer customer = new Customer();
		customer.setEmail(customerEmail);

		Order order = new Order();
		order.setOrderId("order_id");
		order.setTotalOrderValue(BigDecimal.ONE);
		order.setDateCreated(new Timestamp(new Date().getTime()));
		order.setCustomer(customer);

		when(customerPersistenceAdapter.getCustomerByEmail(customerDTO.getEmail())).thenReturn(Optional.of(customer));
		when(orderPersistenceAdapter.getOrderByOrderId(orderId)).thenReturn(Optional.of(order));
		when(productPersistenceAdapter.getProductByProductId(orderDetailRequest.getProductId())).thenReturn(Optional.of(product));

		OrderResponse orderResponse = orderService.createOrder(orderRequest);

		Assert.assertNotNull(orderResponse);
	}

	@Test(expected = BadRequestException.class)
	public void whenCreateOrderWithOrderRequestEmptyThenException() {
		orderService.createOrder(null);
	}

	@Test(expected = BadRequestException.class)
	public void whenCreateOrderWithOrderRequestCustomerEmptyThenException() {
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setCustomer(null);

		orderService.createOrder(orderRequest);
	}

	@Test(expected = BadRequestException.class)
	public void whenCreateOrderWithOrderRequestCustomerEmailEmptyThenException() {

		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setCustomer(new CustomerDTO());

		orderService.createOrder(orderRequest);
	}

	@Test(expected = BadRequestException.class)
	public void whenCreateOrderWithOrderRequestOrderDetailsThenException() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setEmail("customer_email");

		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setCustomer(customerDTO);
		orderRequest.setOrderDetails(Collections.emptyList());

		orderService.createOrder(orderRequest);
	}

	@Test
	public void whenFetchOrdersThenSuccess() {
		Date from = new Date();
		Date to = new Date();

		Product product = new Product();
		product.setProductId("product_Id");
		product.setProductName("product_name");
		product.setProductPrice(BigDecimal.TEN);

		Order order = new Order();
		order.setOrderId("order_id");
		order.setTotalOrderValue(BigDecimal.ONE);
		order.setDateCreated(new Timestamp(new Date().getTime()));
		order.setCustomer(new Customer());
		List<Order> orders = Collections.singletonList(order);

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setSellingPrice(BigDecimal.ONE);
		orderDetail.setQuantity(1);
		orderDetail.setProduct(product);
		List<OrderDetail> orderDetails = Collections.singletonList(orderDetail);

		when(orderPersistenceAdapter.fetchOrdersByDate(from, to)).thenReturn(orders);
		when(orderDetailPersistenceAdapter.fetchOrderDetailsByOrder(order)).thenReturn(orderDetails);

		List<OrderResponse> orderResponses = orderService.fetchOrders(from, to);

		Assert.assertNotNull(orderResponses);
		Assert.assertEquals(1, orderResponses.size());
	}

	@Test(expected = BadRequestException.class)
	public void whenFetchOrdersWithNoFromDateThenException() {
		Date to = new Date();
		orderService.fetchOrders(null, to);
	}

	@Test(expected = BadRequestException.class)
	public void whenFetchOrdersWithNoToDateThenException() {
		Date from = new Date();
		orderService.fetchOrders(from, null);
	}
}