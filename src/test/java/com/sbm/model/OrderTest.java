package com.sbm.model;

import static com.sbm.common.CommonConstants.ORDER_TYPE_SELL;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sbm.common.CommonConstants;
import com.sbm.common.OrderStatus;

public class OrderTest {

	private Order order; 
	@Before
	public void setUp() throws Exception {
		order = new Order(1l, "user1", 3.5, 306.00, ORDER_TYPE_SELL,
				OrderStatus.ACTIVE);
	}

	@Test
	public void testHashCode() {
		
		order.hashCode();
		
	}

	@Test
	public void testEqualsObject() {
		Order eOrder = new Order(1l, "user1", 3.5, 306.00, ORDER_TYPE_SELL,
				OrderStatus.ACTIVE);
		
		assertTrue(order.equals(eOrder));
	}

	@Test
	public void testOrder() {
		Order order = new Order();
		assertNotNull(order);
	}

	@Test
	public void testOrderStringDoubleDoubleString() {
		Order order = new Order("user1", 3.5, 306.00, ORDER_TYPE_SELL);
		assertNotNull(order);
	}

	@Test
	public void testGetOrderId() {
		
		Order order = new Order();
		order.setOrderId(1l);
		assertEquals(Long.valueOf(1), order.getOrderId());
	}

	@Test
	public void testSetOrderId() {
		Order order = new Order();
		order.setOrderId(1l);
		assertEquals(Long.valueOf(1), order.getOrderId());
	}

	@Test
	public void testGetUserId() {
		Order order = new Order();
		order.setUserId("user1");
		assertEquals("user1",order.getUserId());
	}

	@Test
	public void testSetUserId() {
		Order order = new Order();
		order.setUserId("user1");
		assertEquals("user1",order.getUserId());
	}

	@Test
	public void testGetQuantity() {
		Order order = new Order();
		order.setQuantity(3.5);
		assertEquals(Double.valueOf(3.5),order.getQuantity());
	}

	@Test
	public void testSetQuantity() {
		Order order = new Order();
		order.setQuantity(3.5);
		assertEquals(Double.valueOf(3.5),order.getQuantity());
	}

	@Test
	public void testGetPrice() {
		Order order = new Order();
		order.setPrice(306.0);
		assertEquals(Double.valueOf(306.0),order.getPrice());
	}

	@Test
	public void testSetPrice() {
		Order order = new Order();
		order.setPrice(306.0);
		assertEquals(Double.valueOf(306.0),order.getPrice());
	}

	@Test
	public void testGetOrderType() {
		Order order = new Order();
		order.setOrderType("BUY");
		assertEquals("BUY",order.getOrderType());
	}

	@Test
	public void testSetOrderType() {
		Order order = new Order();
		order.setOrderType("SELL");
		assertEquals("SELL",order.getOrderType());
	}

	@Test
	public void testGetOrderStatus() {
		Order order = new Order();
		order.setOrderStatus(OrderStatus.ACTIVE);
		assertEquals(OrderStatus.ACTIVE,order.getOrderStatus());
	}

	@Test
	public void testOrderLongStringDoubleDoubleStringOrderStatus() {
		Order order = new Order(1l, "user1", 3.5, 306.00, ORDER_TYPE_SELL,
				OrderStatus.ACTIVE);
		assertNotNull(order);
	}

	@Test
	public void testSetOrderStatus() {
		Order order = new Order();
		order.setOrderStatus(OrderStatus.ACTIVE);
		assertEquals(OrderStatus.ACTIVE,order.getOrderStatus());
	}

	@Test
	public void testGetStatus() {
		Order order = new Order();
		order.setStatus(CommonConstants.SUCCESS_STATUS);
		assertEquals(CommonConstants.SUCCESS_STATUS,order.getStatus());
	}

	@Test
	public void testSetStatus() {
		Order order = new Order();
		order.setStatus(CommonConstants.SUCCESS_STATUS);
		assertEquals(CommonConstants.SUCCESS_STATUS,order.getStatus());
	}

	@Test
	public void testGetMessage() {
		Order order = new Order();
		order.setMessage(CommonConstants.SUCCESS_MESSAGE);
		assertEquals(CommonConstants.SUCCESS_MESSAGE,order.getMessage());
	}

	@Test
	public void testSetMessage() {
		Order order = new Order();
		order.setMessage(CommonConstants.SUCCESS_MESSAGE);
		assertEquals(CommonConstants.SUCCESS_MESSAGE,order.getMessage());
	}

}
