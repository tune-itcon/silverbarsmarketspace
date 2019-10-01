/**
 * 
 */
package com.sbm.service;

import static com.sbm.common.CommonConstants.ORDER_NOT_FOUND_MESSAGE;
import static com.sbm.common.CommonConstants.ORDER_TYPE_BUY;
import static com.sbm.common.CommonConstants.ORDER_TYPE_SELL;
import static com.sbm.common.CommonConstants.REGISTER_ORDER_ERROR_MESSAGE;
import static com.sbm.common.CommonUtils.isNotNullAndNotEmpty;
import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sbm.common.OrderStatus;
import com.sbm.exception.OrderNotFoundException;
import com.sbm.exception.OrderRegisterException;
import com.sbm.model.Order;
import com.sbm.model.OrderSummary;

/**
 * @author duansubramaniam
 *
 */

@Service
public class OrderServiceImpl implements OrderService {

	/**
	 * Repository cache
	 */
	static final Map<Long,Order> orderRepository;
	
	/**
	 * Sequence id for orders will start with 1
	 */
	static Long orderIdSequence;
	
	static {
		orderRepository = new ConcurrentHashMap<Long,Order>();
		orderIdSequence = 0l;
	}
	public OrderServiceImpl() {
	}
	
	/**
	 * 
	 * @return Order summary
	 * @throws OrderNotFoundException if there are no orders registered or all orders are canceled
	 */

	public OrderSummary getOrdersSummary() throws OrderNotFoundException{
		
		Map<String, List<String>> orderSummaryMap = new HashMap<String, List<String>>();
		orderSummaryMap.put(ORDER_TYPE_SELL, getSummary(ORDER_TYPE_SELL));
		orderSummaryMap.put(ORDER_TYPE_BUY, getSummary(ORDER_TYPE_BUY));
		
		OrderSummary orderSummary = new OrderSummary(orderSummaryMap);
		return orderSummary;
	}
	
	/**
	 * Filters ACTIVE and order type (BUY or SELL). Order type will be treated case insensitive 
	 * @param orderType
	 * @return
	 * @throws OrderNotFoundException 
	 */
	List<String> getSummary(String orderType) throws OrderNotFoundException{
		if(getOrderRepository().values() == null) {
			throw new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE);
		}
		return getOrderRepository().values().stream()
		.filter(order -> order.getOrderType().equals(orderType))
		.filter(order -> order.getOrderStatus().equals(OrderStatus.ACTIVE)) // exclude canceled orders
		.collect(Collectors.groupingBy(Order::getPrice, Collectors.summingDouble(Order::getQuantity)))
		.entrySet() .stream() .sorted(ORDER_TYPE_SELL.equalsIgnoreCase(orderType) ? comparingByKey() : Collections.reverseOrder(Map.Entry.comparingByKey())) .collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new))
		.entrySet().stream().map(order -> composeOrderSummary(order.getKey(),order.getValue())).collect(Collectors.toList());

	}
	/**
	 * Compose order summary
	 * @param price
	 * @param quantity
	 * @return
	 */
	String composeOrderSummary(Double price, Double quantity) {
	    return quantity.toString() + " kg for £" + price.toString();
	}
	
	/**
	 * Registers orders and return the registered order. The order status will ACTIVE and order Id will be assigned. The order id is required to cancel the order.
	 * Mandatory fields for the Order registration: userId, quantity, price, orderType BUY or SELL
	 * @param order
	 * @return Order
	 * @throws OrderRegisterException if order is null or if there is no user id set.
	 */
	public Order registerOrder(Order order) throws OrderRegisterException {
		if(isValid(order)) {
			order.setOrderId(getNextOrderIdSequence());
			// set new order as ACTIVE.
			order.setOrderStatus(OrderStatus.ACTIVE);
			getOrderRepository().put(order.getOrderId(),order);
			return order;
		}
		throw new OrderRegisterException(REGISTER_ORDER_ERROR_MESSAGE);
	}
	
	/**
	 * Order validator will check for mandatory fields userId, quantity, price, orderType BUY or SELL
	 * @param order
	 * @return
	 */
	boolean isValid(Order order) {
		return order != null &&
				isNotNullAndNotEmpty(order.getUserId()) &&
				order.getQuantity() != null &&
				order.getPrice() != null &&
				isNotNullAndNotEmpty(order.getOrderType()) && 
				(ORDER_TYPE_BUY.equalsIgnoreCase(order.getOrderType()) || ORDER_TYPE_SELL.equalsIgnoreCase(order.getOrderType()));
				
	}
	
	/**
	 * Cancels order for a given order with order id. Order Id is mandatory. and returns the canceled order. The order will not be removed out of repository. The order status will be updated to CANCELED
	 * @param order
	 * @return
	 * @throws OrderNotFoundException if order is null or if there is no order id set.
	 */
	public Order cancelOrder(Order order) throws OrderNotFoundException{
		if(order != null && order.getOrderId() != null) {
			Order resultOrder = getOrderRepository().get(order.getOrderId());
			// set order canceled
			resultOrder.setOrderStatus(OrderStatus.CANCELED);
			return resultOrder;
		}
		throw new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE);
	}
	
	Map<Long,Order> getOrderRepository() {
		return orderRepository;
	}
	
	/**
	 * sequest for order Id starts with 1
	 * @return
	 */
	long getNextOrderIdSequence() {
		return ++orderIdSequence;
	}
	

}
