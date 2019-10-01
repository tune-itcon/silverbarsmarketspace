package com.sbm.common;

import static com.sbm.common.CommonConstants.ORDER_TYPE_SELL;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.model.Order;

public class CommonUtilsTest {
	

	@Before
	public void setUp() throws Exception {
	}

	

	@Test
	public void testIsNotNullAndNotEmpty() {
		String testString = null;
		assertFalse(CommonUtils.isNotNullAndNotEmpty(testString));
		
		testString = "";
		assertFalse(CommonUtils.isNotNullAndNotEmpty(testString));
		
		testString = "value";
		assertTrue(CommonUtils.isNotNullAndNotEmpty(testString));
	}

	@Test
	public void testGetModelFromJson() throws IOException {
		Order order = new Order(1l, "user1", 3.5, 306.00, ORDER_TYPE_SELL,
				OrderStatus.ACTIVE);
		ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(order);
        
        Order responseOrder = CommonUtils.getModelFromJson(jsonStr, Order.class);
        
        assertNotNull(responseOrder);
		assertNotNull(responseOrder.getOrderId());
		assertEquals(order.getOrderId(),responseOrder.getOrderId());
		assertEquals(order.getUserId(), responseOrder.getUserId());
		assertEquals(order.getPrice(), responseOrder.getPrice());
		assertEquals(order.getQuantity(), responseOrder.getQuantity());
		assertEquals(order.getOrderStatus(), responseOrder.getOrderStatus());
		assertEquals(order.getOrderType(), responseOrder.getOrderType());
	}

	@Test
	public void testMapToJson() throws IOException {
		Order order = new Order(1l, "user1", 3.5, 306.00, ORDER_TYPE_SELL,
				OrderStatus.ACTIVE);
		
        String jsonStr = CommonUtils.mapToJson(order);
        
        assertNotNull(jsonStr);
        assertTrue(jsonStr.trim().length() > 0);
        
        
        ObjectMapper objectMapper = new ObjectMapper();
		Order responseOrder = objectMapper.readValue(jsonStr, Order.class);
		
        assertNotNull(responseOrder);
		assertNotNull(responseOrder.getOrderId());
		assertEquals(order.getOrderId(),responseOrder.getOrderId());
		assertEquals(order.getUserId(), responseOrder.getUserId());
		assertEquals(order.getPrice(), responseOrder.getPrice());
		assertEquals(order.getQuantity(), responseOrder.getQuantity());
		assertEquals(order.getOrderStatus(), responseOrder.getOrderStatus());
		assertEquals(order.getOrderType(), responseOrder.getOrderType());
	}

}
