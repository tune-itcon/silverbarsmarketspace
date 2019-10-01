/**
 * 
 */
package com.sbm.common;

/**
 * @author duansubramaniam
 *
 */
public interface CommonConstants {
	public static final short UNDEFINED_STATUS = -1;
	public static final short ERROR_STATUS = 0;
	public static final short SUCCESS_STATUS = 1;
	public static final String SUCCESS_MESSAGE = "Successfully Retrieved";
	public static final String REGISTER_SUCCESS_MESSAGE = "Successfully Registered";
	public static final String CANCEL_SUCCESS_MESSAGE = "Successfully Canceled";
	public static final String UNSUCCESSFUL_MESSAGE = "Request Not Successful! ";
	public static final String ORDER_NOT_FOUND_MESSAGE = "Order(s) Not Found";
	public static final String REGISTER_ORDER_ERROR_MESSAGE = "Could Not Register Order! Please check if all mandatory field are set : userId, quantity, price, orderType BUY or SELL";
	public static final String ORDER_TYPE_BUY = "BUY";
	public static final String ORDER_TYPE_SELL = "SELL";
	public static final String DEFAULT_QUANTITY_UNIT = "kg";
	public static final String DEFAULT_CURRENCY = "£";
	
}
