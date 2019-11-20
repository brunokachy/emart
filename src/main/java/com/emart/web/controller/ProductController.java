package com.emart.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.persistence.dto.ProductDTO;
import com.emart.service.ProductService;
import com.emart.web.pojo.ApiResponse;
import com.emart.web.pojo.ProductRequest;
import io.swagger.annotations.ApiOperation;

/**
 * @author Bruno Okafor 2019-11-20
 */

@RestController
@RequestMapping(value = "emart/api/product")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	private ProductService productService;

	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "Create Product")
	@PostMapping(value = {"/create_product"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody ProductRequest productRequest) {
		log.info("CREATE PRODUCT: {}", productRequest.toString());
		ApiResponse<ProductDTO> apiResponse = new ApiResponse<>();
		ProductDTO response = productService.createProduct(productRequest);
		apiResponse.setMessage("Product created successfully");
		apiResponse.setData(response);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Update Product")
	@PostMapping(value = {"/update_product"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@RequestBody ProductRequest productRequest) {
		log.info("UPDATE PRODUCT: {}", productRequest.toString());
		ApiResponse<ProductDTO> apiResponse = new ApiResponse<>();
		ProductDTO response = productService.updateProduct(productRequest);
		apiResponse.setMessage("Product updated successfully");
		apiResponse.setData(response);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Fetch Products")
	@GetMapping(value = {"/fetch_products"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<List<ProductDTO>>> fetchProducts() {
		log.info("FETCH PRODUCT");
		ApiResponse<List<ProductDTO>> apiResponse = new ApiResponse<>();
		List<ProductDTO> response = productService.fetchProducts();
		apiResponse.setMessage("Product fetched successfully");
		apiResponse.setData(response);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}
