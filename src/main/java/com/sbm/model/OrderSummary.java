/**
 * 
 */
package com.sbm.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author duansubramaniam
 *
 */
public class OrderSummary extends AbstractResponse{
	Map<String,List<String>> orderSummary;

	
	/**
	 * 
	 */
	public OrderSummary() {
		
	}


	/**
	 * @param orderSummary
	 */
	public OrderSummary(Map<String, List<String>> orderSummary) {
		this.orderSummary = orderSummary;
	}


	/**
	 * @return the orderSummary
	 */
	public Map<String, List<String>> getOrderSummary() {
		return orderSummary;
	}


	/**
	 * @param orderSummary the orderSummary to set
	 */
	public void setOrderSummary(Map<String, List<String>> orderSummary) {
		this.orderSummary = orderSummary;
	}


	@Override
	public int hashCode() {
		return Objects.hash(orderSummary);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderSummary other = (OrderSummary) obj;
		return Objects.equals(orderSummary, other.orderSummary);
	}


	


	
	
}
