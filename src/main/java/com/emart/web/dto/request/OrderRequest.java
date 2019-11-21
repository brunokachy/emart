package com.emart.web.dto.request;

import java.io.Serializable;
import java.util.List;

import com.emart.web.dto.CustomerDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bruno Okafor 2019-11-21
 */
@Getter
@Setter
@ToString
public class OrderRequest implements Serializable {

	private CustomerDTO customer;

	private List<OrderDetailRequest> orderDetails;
}
