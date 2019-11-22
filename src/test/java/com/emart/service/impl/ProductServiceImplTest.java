package com.emart.service.impl;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emart.exceptions.BadRequestException;
import com.emart.exceptions.NotFoundException;
import com.emart.persistence.entity.Product;
import com.emart.persistence.service.ProductPersistenceAdapter;
import com.emart.web.dto.request.CreateProductRequest;
import com.emart.web.dto.request.UpdateProductRequest;
import com.emart.web.dto.response.ProductResponse;

/**
 * @author Bruno Okafor 2019-11-21
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceImplTest {

	private static final String PRODUCT_ID = "product_id";
	private static final String PRODUCT_NAME = "product_name";
	private static final String PRODUCT_DESCRIPTION = "product_description";

	@InjectMocks
	ProductServiceImpl productService;
	@Mock
	private ProductPersistenceAdapter productPersistenceAdapter;

	@Test
	public void whenCreateProductThenSuccessful() {
		CreateProductRequest createProductRequest = new CreateProductRequest();
		createProductRequest.setProductDescription(PRODUCT_DESCRIPTION);
		createProductRequest.setProductName(PRODUCT_NAME);
		createProductRequest.setProductPrice(2.0);

		ProductResponse productResponse = productService.createProduct(createProductRequest);

		Assert.assertNotNull(productResponse);
		Assert.assertEquals(createProductRequest.getProductPrice(), productResponse.getProductPrice());
		Assert.assertEquals(createProductRequest.getProductDescription(), productResponse.getProductDescription());
		Assert.assertEquals(createProductRequest.getProductName(), productResponse.getProductName());
	}

	@Test(expected = BadRequestException.class)
	public void whenCreateProductWithNoProductNameThenException() {
		CreateProductRequest createProductRequest = new CreateProductRequest();
		createProductRequest.setProductDescription(PRODUCT_DESCRIPTION);
		createProductRequest.setProductPrice(2.0);

		productService.createProduct(createProductRequest);
	}

	@Test(expected = BadRequestException.class)
	public void whenCreateProductWithNoProductPriceThenException() {
		CreateProductRequest createProductRequest = new CreateProductRequest();
		createProductRequest.setProductDescription(PRODUCT_DESCRIPTION);
		createProductRequest.setProductName(PRODUCT_NAME);

		productService.createProduct(createProductRequest);
	}

	@Test
	public void whenUpdateProductThenSuccessful() {
		UpdateProductRequest updateProductRequest = new UpdateProductRequest();
		updateProductRequest.setProductDescription(PRODUCT_DESCRIPTION);
		updateProductRequest.setProductName(PRODUCT_NAME);
		updateProductRequest.setProductPrice(2.0);
		updateProductRequest.setProductId(PRODUCT_ID);

		Product product = new Product();
		product.setId(1L);
		product.setProductPrice(BigDecimal.ONE);
		product.setProductName(PRODUCT_NAME);
		product.setProductDescription(PRODUCT_DESCRIPTION);
		product.setProductId(PRODUCT_ID);

		when(productPersistenceAdapter.getProductByProductId(updateProductRequest.getProductId())).thenReturn(Optional.of(product));

		ProductResponse productResponse = productService.updateProduct(updateProductRequest);

		Assert.assertNotNull(productResponse);
		Assert.assertEquals(product.getProductId(), productResponse.getProductId());
		Assert.assertEquals(product.getProductDescription(), productResponse.getProductDescription());
		Assert.assertEquals(product.getProductName(), product.getProductName());
	}

	@Test(expected = BadRequestException.class)
	public void whenUpdateProductWithNoProductNameThenException() {
		UpdateProductRequest updateProductRequest = new UpdateProductRequest();
		updateProductRequest.setProductDescription(PRODUCT_DESCRIPTION);
		updateProductRequest.setProductPrice(2.0);
		updateProductRequest.setProductId(PRODUCT_ID);

		productService.updateProduct(updateProductRequest);
	}

	@Test(expected = BadRequestException.class)
	public void whenUpdateProductWithNoProductPriceThenException() {
		UpdateProductRequest updateProductRequest = new UpdateProductRequest();
		updateProductRequest.setProductDescription(PRODUCT_DESCRIPTION);
		updateProductRequest.setProductName(PRODUCT_NAME);
		updateProductRequest.setProductId(PRODUCT_ID);

		productService.updateProduct(updateProductRequest);
	}

	@Test(expected = BadRequestException.class)
	public void whenUpdateProductWithNoProductIdThenException() {
		UpdateProductRequest updateProductRequest = new UpdateProductRequest();
		updateProductRequest.setProductDescription(PRODUCT_DESCRIPTION);
		updateProductRequest.setProductName(PRODUCT_NAME);
		updateProductRequest.setProductPrice(2.0);

		productService.updateProduct(updateProductRequest);
	}

	@Test(expected = NotFoundException.class)
	public void whenUpdateProductWitProductNotFoundThenException() {
		UpdateProductRequest updateProductRequest = new UpdateProductRequest();
		updateProductRequest.setProductDescription(PRODUCT_DESCRIPTION);
		updateProductRequest.setProductName(PRODUCT_NAME);
		updateProductRequest.setProductPrice(2.0);
		updateProductRequest.setProductId(PRODUCT_ID);

		when(productPersistenceAdapter.getProductByProductId(updateProductRequest.getProductId())).thenReturn(Optional.empty());

		productService.updateProduct(updateProductRequest);
	}

}