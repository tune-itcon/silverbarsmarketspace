/**
 * 
 */
package com.sbm.model;



import java.util.Objects;

import com.sbm.common.OrderStatus;


/**
 * @author duansubramaniam
 *
 */
public class Order extends AbstractResponse{
	private Long orderId;
	private String userId;
	private Double quantity;
	private Double price;
	private String orderType;
	private OrderStatus orderStatus;
	/**
	 * 
	 */
	public Order() {

	}
	/**
	 * @param userId
	 * @param quantity
	 * @param price
	 * @param orderType
	 */
	public Order(String userId, Double quantity, Double price, String orderType) {
		this.userId = userId;
		this.quantity = quantity;
		this.price = price;
		this.orderType = orderType;
	}
	
	
	/**
	 * @param orderId
	 * @param userId
	 * @param quantity
	 * @param price
	 * @param orderType
	 * @param orderStatus
	 */
	public Order(Long orderId, String userId, Double quantity, Double price, String orderType,
			OrderStatus orderStatus) {
		this.orderId = orderId;
		this.userId = userId;
		this.quantity = quantity;
		this.price = price;
		this.orderType = orderType;
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the orderStatus
	 */
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(orderId, orderStatus, orderType, price, quantity, userId);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(orderId, other.orderId) && orderStatus == other.orderStatus
				&& Objects.equals(orderType, other.orderType) && Objects.equals(price, other.price)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(userId, other.userId);
	}

}
