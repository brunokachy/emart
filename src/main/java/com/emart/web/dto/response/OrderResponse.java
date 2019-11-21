package com.emart.web.dto.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.emart.web.dto.CustomerDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bruno Okafor 2019-11-19
 */
@Getter
@Setter
@ToString
public class OrderResponse implements Serializable {

	private String orderId;

	private CustomerDTO customer;

	private Double totalOrderValue;

	private Date dateCreated;

	private List<OrderDetailResponse> orderDetails;

}
