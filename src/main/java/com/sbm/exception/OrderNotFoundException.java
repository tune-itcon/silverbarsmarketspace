/**
 * 
 */
package com.sbm.exception;

/**
 * @author duansubramaniam
 * Exception for Orders not found
 */
public class OrderNotFoundException extends Exception {
	public OrderNotFoundException(String message) {
		super(message);
	}
}
