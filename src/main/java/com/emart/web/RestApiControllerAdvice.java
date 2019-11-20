package com.emart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.emart.exceptions.BadRequestException;
import com.emart.exceptions.NotFoundException;
import com.emart.web.pojo.ApiResponse;

/**
 * @author Bruno Okafor 2019-11-20
 */
@ControllerAdvice(basePackages = {"com.emart.web.controller"})
public class RestApiControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(RestApiControllerAdvice.class);

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity handleException(Exception exception) {
		log.debug("Exception: {}", exception.getMessage());
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Sorry, unable to process request at the moment.");
		return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity handleRuntimeException(RuntimeException exception) {
		log.error("RuntimeException: {}", exception.getMessage());
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Sorry, an error occurred while processing your request..");
		return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity handleBadRequestException(BadRequestException e) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		log.debug("error message: {}", e.getMessage());
		apiResponse.setMessage(e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity handleNotFoundException(NotFoundException e) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		log.debug("error message: {}", e.getMessage());
		apiResponse.setMessage(e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}
}
