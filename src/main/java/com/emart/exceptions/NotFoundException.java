package com.emart.exceptions;

/**
 * @author Bruno Okafor 2019-11-19
 */
public class NotFoundException extends RuntimeException {
	public NotFoundException(String errorMessage) {
		super(errorMessage);
	}
}

