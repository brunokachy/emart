package com.emart.web.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emart.service.OrderService;
import com.emart.web.dto.ApiResponse;
import com.emart.web.dto.request.OrderRequest;
import com.emart.web.dto.response.OrderResponse;
import io.swagger.annotations.ApiOperation;

/**
 * @author Bruno Okafor 2019-11-20
 */

@RestController
@RequestMapping(value = "emart/api/order")
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	private OrderService orderService;

	public OrderController(final OrderService orderService) {
		this.orderService = orderService;
	}

	@ApiOperation(value = "Create Order")
	@PostMapping(value = {"/create_order"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<OrderResponse>> createProduct(@RequestBody OrderRequest orderRequest) {
		log.info("CREATE ORDER: {}", orderRequest);
		ApiResponse<OrderResponse> apiResponse = new ApiResponse<>();
		OrderResponse response = orderService.createOrder(orderRequest);
		apiResponse.setMessage("Order created successfully");
		apiResponse.setData(response);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieve Orders by Date Created")
	@GetMapping(value = {"/retrieve_order"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<List<OrderResponse>>> createProduct(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date from,
																		  @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date to) {
		log.info("RETRIEVE ORDER");
		ApiResponse<List<OrderResponse>> apiResponse = new ApiResponse<>();
		List<OrderResponse> response = orderService.fetchOrders(from, to);
		apiResponse.setMessage("Order retrieved successfully");
		apiResponse.setData(response);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}


}
