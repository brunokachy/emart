package com.emart.web.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Setter
public class OrderDTO implements Serializable {

	private String orderId;

	private CustomerDTO customer;

	private Double totalOrderValue;

	private Date dateCreated;

	private List<OrderDetailDTO> orderDetails;

}