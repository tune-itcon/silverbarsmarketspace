/**
 * 
 */
package com.sbm.service;

import com.sbm.exception.OrderNotFoundException;
import com.sbm.exception.OrderRegisterException;
import com.sbm.model.Order;
import com.sbm.model.OrderSummary;

/**
 * @author duansubramaniam
 * Order Service interface to register, cancel and get order summary. 
 */
public interface OrderService {
	/**
	 * 
	 * @return Order summary
	 * @throws OrderNotFoundException if there are no orders registered or all orders are canceled
	 */
	public OrderSummary getOrdersSummary()throws OrderNotFoundException;
	
	/**
	 * Registers orders and return the registered order. The order status will ACTIVE and order Id will be assigned. The order id is required to cancel the order.
	 * Mandatory fields for the Order registration: userId, quantity, quantity unit (if not provided the default kg will be assigned), price, currency (if not provided default £ will be assigned), orderType BUY or SELL
	 * @param order
	 * @return Order
	 * @throws OrderRegisterException if order is null or if there is no user id set.
	 */
	public Order registerOrder(Order order)throws OrderRegisterException;
	
	/**
	 * Cancels order for a given order with order id. Order Id is mandatory. and returns the canceled order. The order will not be removed out of repository. The order status will be updated to CANCELED
	 * @param order
	 * @return
	 * @throws OrderNotFoundException if order is null or if there is no order id set.
	 */
	public Order cancelOrder(Order order)throws OrderNotFoundException;
	

}
