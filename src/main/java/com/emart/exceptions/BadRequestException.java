package com.emart.exceptions;

/**
 * @author Bruno Okafor 2019-11-19
 */
public class BadRequestException extends RuntimeException {
	public BadRequestException(String errorMessage) {
		super(errorMessage);
	}
}