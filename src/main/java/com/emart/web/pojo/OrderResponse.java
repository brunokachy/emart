package com.emart.web.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.emart.persistence.dto.CustomerDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Okafor 2019-11-20
 */
@Getter
@Setter
public class OrderResponse implements Serializable {

	private Long id;

	private CustomerDTO customer;

	private Double totalOrderValue;

	private Date dateCreated;

	private List<ProductRequest> products;

}
