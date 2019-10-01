package com.sbm.service;

import static com.sbm.common.CommonConstants.ORDER_TYPE_BUY;
import static com.sbm.common.CommonConstants.ORDER_TYPE_SELL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sbm.common.OrderStatus;
import com.sbm.exception.OrderNotFoundException;
import com.sbm.exception.OrderRegisterException;
import com.sbm.model.Order;
import com.sbm.model.OrderSummary;

public class OrderServiceTest {

	@Mock
	OrderService orderService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetOrdersSummary() throws OrderNotFoundException {
		List<String> summaryList = Arrays.asList("5.5 kg for £306.00", "1.5 kg for £307.00");
		Map<String,List<String>> summaryMap = new HashMap<String,List<String>>();
		summaryMap.put(ORDER_TYPE_BUY, summaryList);
		OrderSummary orderSummary = new OrderSummary(summaryMap);
		
		when(orderService.getOrdersSummary()).thenReturn(orderSummary);
		
		OrderSummary response = orderService.getOrdersSummary();
		
		assertNotNull("Expected OrderSummary object not null",response);
		assertNotNull("Expected orderSummary content not null",response.getOrderSummary());
		
		assertTrue("Expected orderSummary content to have a value",response.getOrderSummary().size() > 0);

	}

	@Test
	public void testRegisterOrder() throws OrderRegisterException {
		Order order = new Order(1l, "user1", 3.5, 306.00, ORDER_TYPE_SELL,
				OrderStatus.ACTIVE);
		
		when(orderService.registerOrder(order)).thenReturn(order);
		
		Order response = orderService.registerOrder(order);
		
		assertNotNull("Expected Order object not null",response);
		assertNotNull("Expected Order id not null",response.getOrderId());
		assertEquals(order.getOrderId(),response.getOrderId());
		assertEquals(order.getUserId(), response.getUserId());
		assertEquals(order.getPrice(), response.getPrice());
		assertEquals(order.getQuantity(), response.getQuantity());
		assertEquals(order.getOrderStatus(), response.getOrderStatus());
		assertEquals(order.getOrderType(), response.getOrderType());
		
	}

	@Test
	public void testCancelOrder() throws OrderNotFoundException {
		Order order = new Order(1l, "user1", 3.5, 306.00, ORDER_TYPE_BUY,
				OrderStatus.CANCELED);
		
		when(orderService.cancelOrder(order)).thenReturn(order);
		
		Order response = orderService.cancelOrder(order);
		
		assertNotNull("Expected Order object not null",response);
		assertNotNull("Expected Order id not null",response.getOrderId());
		assertEquals(order.getOrderId(),response.getOrderId());
		assertEquals(order.getUserId(), response.getUserId());
		assertEquals(order.getPrice(), response.getPrice());
		assertEquals(order.getQuantity(), response.getQuantity());
		assertEquals(order.getOrderStatus(), response.getOrderStatus());
		assertEquals(order.getOrderType(), response.getOrderType());
	}

}
