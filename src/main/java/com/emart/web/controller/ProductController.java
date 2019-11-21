package com.emart.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emart.service.ProductService;
import com.emart.web.dto.ApiResponse;
import com.emart.web.dto.ProductDTO;
import com.emart.web.dto.ProductListResponse;
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
	public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody ProductDTO product) {
		log.info("CREATE PRODUCT: {}", product);
		ApiResponse<ProductDTO> apiResponse = new ApiResponse<>();
		ProductDTO response = productService.createProduct(product);
		apiResponse.setMessage("Product created successfully");
		apiResponse.setData(response);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Update Product")
	@PostMapping(value = {"/update_product"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@RequestBody ProductDTO product) {
		log.info("UPDATE PRODUCT: {}", product);
		ApiResponse<ProductDTO> apiResponse = new ApiResponse<>();
		ProductDTO response = productService.updateProduct(product);
		apiResponse.setMessage("Product updated successfully");
		apiResponse.setData(response);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Fetch Products")
	@GetMapping(value = {"/fetch_products"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<ProductListResponse>> fetchProducts(@RequestParam(name = "start") int start, @RequestParam(name = "limit") int limit) {
		log.info("FETCH PRODUCT");
		ApiResponse<ProductListResponse> apiResponse = new ApiResponse<>();
		ProductListResponse response = productService.fetchProducts(start, limit);
		apiResponse.setMessage("Product fetched successfully");
		apiResponse.setData(response);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}
