package com.sbm.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OrderStatusTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		OrderStatus status = OrderStatus.ACTIVE;
	    assertEquals(OrderStatus.valueOf("ACTIVE"), status);
	       
	    status = OrderStatus.CANCELED;
	    assertEquals(OrderStatus.valueOf("CANCELED"), status);
	}

}
