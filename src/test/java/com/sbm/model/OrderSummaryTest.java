package com.sbm.model;

import static com.sbm.common.CommonConstants.ORDER_TYPE_BUY;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class OrderSummaryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHashCode() {
		List<String> summaryList = Arrays.asList("5.5 kg for £306.00", "1.5 kg for £307.00");
		Map<String,List<String>> summaryMap = new HashMap<String,List<String>>();
		summaryMap.put(ORDER_TYPE_BUY, summaryList);
		OrderSummary orderSummary = new OrderSummary(summaryMap);
		orderSummary.hashCode();
	}

	@Test
	public void testEqualsObject() {
		List<String> summaryList = Arrays.asList("5.5 kg for £306.00", "1.5 kg for £307.00");
		Map<String,List<String>> summaryMap = new HashMap<String,List<String>>();
		summaryMap.put(ORDER_TYPE_BUY, summaryList);
		OrderSummary orderSummary = new OrderSummary(summaryMap);
		
		OrderSummary eOrderSummary = new OrderSummary(summaryMap);
		
		assertTrue(orderSummary.equals(eOrderSummary));
	}

	@Test
	public void testOrderSummary() {
		OrderSummary orderSummary = new OrderSummary();
		assertNotNull(orderSummary);
	}

	@Test
	public void testOrderSummaryMapOfStringListOfString() {
		List<String> summaryList = Arrays.asList("5.5 kg for £306.00", "1.5 kg for £307.00");
		Map<String,List<String>> summaryMap = new HashMap<String,List<String>>();
		summaryMap.put(ORDER_TYPE_BUY, summaryList);
		OrderSummary orderSummary = new OrderSummary(summaryMap);
	}

	@Test
	public void testGetOrderSummary() {
		List<String> summaryList = Arrays.asList("5.5 kg for £306.00", "1.5 kg for £307.00");
		Map<String,List<String>> summaryMap = new HashMap<String,List<String>>();
		summaryMap.put(ORDER_TYPE_BUY, summaryList);
		OrderSummary orderSummary = new OrderSummary();
		orderSummary.setOrderSummary(summaryMap);
		assertEquals(summaryMap, orderSummary.getOrderSummary());
	}

	@Test
	public void testSetOrderSummary() {
		List<String> summaryList = Arrays.asList("5.5 kg for £306.00", "1.5 kg for £307.00");
		Map<String,List<String>> summaryMap = new HashMap<String,List<String>>();
		summaryMap.put(ORDER_TYPE_BUY, summaryList);
		OrderSummary orderSummary = new OrderSummary();
		orderSummary.setOrderSummary(summaryMap);
		assertEquals(summaryMap, orderSummary.getOrderSummary());
	}

}
