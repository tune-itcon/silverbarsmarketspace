package com.sbm.service;

import static com.sbm.common.CommonConstants.ORDER_NOT_FOUND_MESSAGE;
import static com.sbm.common.CommonConstants.ORDER_TYPE_BUY;
import static com.sbm.common.CommonConstants.ORDER_TYPE_SELL;
import static com.sbm.common.CommonConstants.REGISTER_ORDER_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.sbm.common.OrderStatus;
import com.sbm.exception.OrderNotFoundException;
import com.sbm.exception.OrderRegisterException;
import com.sbm.model.Order;
import com.sbm.model.OrderSummary;

public class OrderServiceImplTest {
	
	@InjectMocks
	OrderServiceImpl orderService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testOrderServiceImpl() {
		OrderService orderServiceImpl = new OrderServiceImpl();
		assertNotNull("Expected OrderServiceImpl object not null",orderServiceImpl);
		assertNotNull("Expected Injected OrderService object not null",orderService);
	}
	
	private Order getOrder(String userId, Double quantity, Double price, String orderType) {
		return new Order(userId, quantity, price, orderType);
	}
	
	private void registerSellOrders() throws OrderRegisterException {
		orderService.registerOrder(getOrder("user1", 3.5, 306.0, ORDER_TYPE_SELL));
		orderService.registerOrder(getOrder("user2", 1.2, 310.0, ORDER_TYPE_SELL));
		orderService.registerOrder(getOrder("user3", 1.5, 307.0, ORDER_TYPE_SELL));
		orderService.registerOrder(getOrder("user4", 2.0, 306.0, ORDER_TYPE_SELL));
	}
	
	private void registerBuyOrders() throws OrderRegisterException {
		orderService.registerOrder(getOrder("user1", 4.5, 406.0, ORDER_TYPE_BUY));
		orderService.registerOrder(getOrder("user2", 2.2, 410.0, ORDER_TYPE_BUY));
		orderService.registerOrder(getOrder("user3", 2.5, 407.0, ORDER_TYPE_BUY));
		orderService.registerOrder(getOrder("user4", 3.0, 406.0, ORDER_TYPE_BUY));
	}
	
	private void testSellOrderSummary() throws OrderNotFoundException {
		OrderSummary response = orderService.getOrdersSummary();
		
		assertNotNull("Expected OrderSummary object not null",response);
		
		Map<String,List<String>> orderSummaryMap = response.getOrderSummary();
		assertNotNull("Expected orderSummaryMap not null",orderSummaryMap);
		
		assertTrue("Expected orderSummaryMap content to have a value",orderSummaryMap.size() > 0);
		
		List<String> sellOrderSummaryList = orderSummaryMap.get(ORDER_TYPE_SELL);
		assertNotNull("Expected sellOrderSummaryList not null",sellOrderSummaryList);
		
		assertTrue("Expected sellOrderSummaryList content to have a value",sellOrderSummaryList.size() > 0);
		
		assertEquals("Expected 5.5 kg for £306.0","5.5 kg for £306.0",sellOrderSummaryList.get(0));
		assertEquals("Expected 1.5 kg for £307.0","1.5 kg for £307.0",sellOrderSummaryList.get(1));
		assertEquals("Expected 1.2 kg for £310.0","1.2 kg for £310.0",sellOrderSummaryList.get(2));

	}
	
	private void testBuyOrderSummary() throws OrderNotFoundException {
		OrderSummary response = orderService.getOrdersSummary();
		
		assertNotNull("Expected OrderSummary object not null",response);
		
		Map<String,List<String>> orderSummaryMap = response.getOrderSummary();
		assertNotNull("Expected orderSummaryMap not null",orderSummaryMap);
		
		assertTrue("Expected orderSummaryMap content to have a value",orderSummaryMap.size() > 0);
		
		List<String> buyOrderSummaryList = orderSummaryMap.get(ORDER_TYPE_BUY);
		assertNotNull("Expected buyOrderSummaryList not null",buyOrderSummaryList);
		
		assertTrue("Expected buyOrderSummaryList content to have a value",buyOrderSummaryList.size() > 0);
		
		assertEquals("Expected 2.2 kg for £410.0","2.2 kg for £410.0",buyOrderSummaryList.get(0));
		assertEquals("Expected 2.5 kg for £407.0","2.5 kg for £407.0",buyOrderSummaryList.get(1));
		assertEquals("Expected 7.5 kg for £406.0","7.5 kg for £406.0",buyOrderSummaryList.get(2));

	}
	
	private void clearOrderRepository() {
		orderService.getOrderRepository().clear();
		orderService.orderIdSequence = 0l;
	}

	/**
	 * get complete summary test
	 * @throws OrderRegisterException
	 * @throws OrderNotFoundException
	 */
	@Test
	public void testGetOrdersSummary() throws OrderRegisterException, OrderNotFoundException {
		clearOrderRepository();
		registerSellOrders();
		registerBuyOrders();
		
		testSellOrderSummary();	
	
		testBuyOrderSummary();
	}
	
	/**
	 * get sell summary test
	 * @throws OrderRegisterException
	 * @throws OrderNotFoundException
	 */
	@Test
	public void testGetSellOrdersSummary() throws OrderRegisterException, OrderNotFoundException {
		clearOrderRepository();
		registerSellOrders();
		testSellOrderSummary();	
	}
	
	/**
	 * get buy summary test
	 * @throws OrderRegisterException
	 * @throws OrderNotFoundException
	 */
	@Test
	public void testGetBuyOrdersSummary() throws OrderRegisterException, OrderNotFoundException {
		clearOrderRepository();
		registerBuyOrders();
		testBuyOrderSummary();	
	}

	/**
	 * get sell summary test
	 * @throws OrderNotFoundException
	 */
	private void getSellSummary() throws OrderNotFoundException {
		List<String> sellOrderSummaryList = orderService.getSummary(ORDER_TYPE_SELL);
				
		assertNotNull("Expected sellOrderSummaryList not null",sellOrderSummaryList);
				
		assertTrue("Expected sellOrderSummaryList content to have a value",sellOrderSummaryList.size() > 0);
				
		assertEquals("Expected 5.5 kg for £306.0","5.5 kg for £306.0",sellOrderSummaryList.get(0));
		assertEquals("Expected 1.5 kg for £307.0","1.5 kg for £307.0",sellOrderSummaryList.get(1));
		assertEquals("Expected 1.2 kg for £310.0","1.2 kg for £310.0",sellOrderSummaryList.get(2));

	}
	
	
	/**
	 * get buy summary test
	 * @throws OrderNotFoundException
	 */
	private void getBuySummary() throws OrderNotFoundException {
		List<String> buyOrderSummaryList = orderService.getSummary(ORDER_TYPE_BUY);
				
		assertNotNull("Expected buyOrderSummaryList not null",buyOrderSummaryList);
		
		assertTrue("Expected buyOrderSummaryList content to have a value",buyOrderSummaryList.size() > 0);
		
		assertEquals("Expected 2.2 kg for £410.0","2.2 kg for £410.0",buyOrderSummaryList.get(0));
		assertEquals("Expected 2.5 kg for £407.0","2.5 kg for £407.0",buyOrderSummaryList.get(1));
		assertEquals("Expected 7.5 kg for £406.0","7.5 kg for £406.0",buyOrderSummaryList.get(2));

	}
	
	/**
	 * register buy and sell orders and get complete summary
	 * @throws OrderRegisterException
	 * @throws OrderNotFoundException
	 */
	@Test
	public void testGetSummary() throws OrderRegisterException, OrderNotFoundException {
		clearOrderRepository();
		registerSellOrders();
		registerBuyOrders();
		getSellSummary();
		getBuySummary();
	}
	
	/**
	 * get buy summary test
	 * @throws OrderRegisterException
	 * @throws OrderNotFoundException
	 */
	@Test
	public void testGetBuySummary() throws OrderRegisterException, OrderNotFoundException {
		clearOrderRepository();
		registerBuyOrders();
		getBuySummary();
	}
	
	/**
	 * Clear order repository and get order summary. If repository is empty orders not found message is thrown.
	 */
	@Test
	public void testGetSummaryNotFound() {
		clearOrderRepository();
		
		try {
			orderService.getSummary(ORDER_TYPE_BUY);
		}catch(Exception e) {
			assertThat(e)
        .isInstanceOf(OrderNotFoundException.class)
        .hasMessage(ORDER_NOT_FOUND_MESSAGE);
		}
	}
	
	/*
	 * register only buy orders and get sell order summary. Sell order summary should be empty.
	 */
	@Test
	public void testGetSummarySellIsEmpty() throws OrderRegisterException, OrderNotFoundException {
		clearOrderRepository();
		registerBuyOrders();
		List<String> orderList =	orderService.getSummary(ORDER_TYPE_SELL);
		assertTrue("Expected orderList content should be empty",orderList.size() == 0);
	}
	
	/**
	 * get sell summary test
	 * @throws OrderRegisterException
	 * @throws OrderNotFoundException
	 */
	@Test
	public void testGetSellSummary() throws OrderRegisterException, OrderNotFoundException {
		clearOrderRepository();
		registerSellOrders();
		getSellSummary();
	}

	/**
	 * compose order summary text test
	 */
	@Test
	public void testComposeOrderSummary() {
		String orderSummary = orderService.composeOrderSummary(410.0, 2.2);
		assertNotNull("Expected orderSummary not null",orderSummary);
		assertEquals("Expected 2.2 kg for £410.0","2.2 kg for £410.0",orderSummary);
	}

	/**
	 * Register order without user id. User id is mandatory. should throw register order error message
	 */
	@Test
	public void testRegisterOrderWithOrderRegisterException() {
		clearOrderRepository();
		try {
			Order order = getOrder(null, 4.5, 406.0, ORDER_TYPE_BUY);
			orderService.registerOrder(order);
		}catch(Exception e) {
			assertThat(e)
        .isInstanceOf(OrderRegisterException.class)
        .hasMessage(REGISTER_ORDER_ERROR_MESSAGE);
		}
	}
	
	/**
	 * Register order without price. price is mandatory. should throw register order error message
	 */
	@Test
	public void testRegisterOrderWithoutPriceOrderRegisterException() {
		clearOrderRepository();
		try {
			Order order = getOrder("user1", 4.5, null, ORDER_TYPE_BUY);
			orderService.registerOrder(order);
		}catch(Exception e) {
			assertThat(e)
        .isInstanceOf(OrderRegisterException.class)
        .hasMessage(REGISTER_ORDER_ERROR_MESSAGE);
		}
	}
	
	/**
	 * Register order without quantity. quantity is mandatory. should throw register order error message
	 */
	@Test
	public void testRegisterOrderWithoutQuantityOrderRegisterException() {
		clearOrderRepository();
		try {
			Order order = getOrder("user1", null, 406.0, ORDER_TYPE_BUY);
			orderService.registerOrder(order);
		}catch(Exception e) {
			assertThat(e)
        .isInstanceOf(OrderRegisterException.class)
        .hasMessage(REGISTER_ORDER_ERROR_MESSAGE);
		}
	}
	
	/**
	 * Register order without order type. order type is mandatory. should throw register order error message
	 */
	@Test
	public void testRegisterOrderWithoutOrderTypeOrderRegisterException() {
		clearOrderRepository();
		try {
			Order order = getOrder("user1", 4.5, 406.0, null);
			orderService.registerOrder(order);
		}catch(Exception e) {
			assertThat(e)
        .isInstanceOf(OrderRegisterException.class)
        .hasMessage(REGISTER_ORDER_ERROR_MESSAGE);
		}
	}
	
	/**
	 * Register order with misspelled order type. order type is mandatory. should throw register order error message
	 */
	@Test
	public void testRegisterOrderMisspelledOrderTypeOrderRegisterException() {
		clearOrderRepository();
		try {
			Order order = getOrder("user1", 4.5, 406.0, "BAY");
			orderService.registerOrder(order);
		}catch(Exception e) {
			assertThat(e)
        .isInstanceOf(OrderRegisterException.class)
        .hasMessage(REGISTER_ORDER_ERROR_MESSAGE);
		}
	}
	
	/**
	 * Register order without mandatory fields. mandatory fields are userId, quantity, price, orderType. should throw register order error message
	 */
	@Test
	public void testRegisterOrderWithoutMandatoryFieldsOrderRegisterException() {
		clearOrderRepository();
		try {
			Order order = getOrder(null, null, null, null);
			orderService.registerOrder(order);
		}catch(Exception e) {
			assertThat(e)
        .isInstanceOf(OrderRegisterException.class)
        .hasMessage(REGISTER_ORDER_ERROR_MESSAGE);
		}
	}
	
	/**
	 * Register order test
	 * @throws OrderRegisterException
	 */
	@Test
	public void testRegisterOrder() throws OrderRegisterException {
		clearOrderRepository();
		Order order = getOrder("user1", 4.5, 406.0, ORDER_TYPE_BUY);
		Order response = orderService.registerOrder(order);
		assertNotNull("Expected Order object not null",response);
		assertNotNull("Expected Order id not null",response.getOrderId());
		assertEquals("Expected Order id 1",order.getOrderId(),response.getOrderId());
		assertEquals("Expected user id user1",order.getUserId(), response.getUserId());
		assertEquals("Expected price 406.0",order.getPrice(), response.getPrice());
		assertEquals("Expected quantity 4.5",order.getQuantity(), response.getQuantity());
		assertEquals("Expected status Active",OrderStatus.ACTIVE, response.getOrderStatus());
		assertEquals("Expected type Buy",ORDER_TYPE_BUY, response.getOrderType());

		order = getOrder("user2", 2.2, 410.0, ORDER_TYPE_BUY);
		response = orderService.registerOrder(order);
		assertNotNull("Expected Order object not null",response);
		assertNotNull("Expected Order id not null",response.getOrderId());
		assertEquals("Expected Order id 2",order.getOrderId(),response.getOrderId());
		assertEquals("Expected user id user2",order.getUserId(), response.getUserId());
		assertEquals("Expected price 410.0",order.getPrice(), response.getPrice());
		assertEquals("Expected quantity 2.2",order.getQuantity(), response.getQuantity());
		assertEquals("Expected status Active",OrderStatus.ACTIVE, response.getOrderStatus());
		assertEquals("Expected type Buy",ORDER_TYPE_BUY, response.getOrderType());
		
		order = getOrder("user1", 3.5, 306.0, ORDER_TYPE_SELL);
		response = orderService.registerOrder(order);
		assertNotNull("Expected Order object not null",response);
		assertNotNull("Expected Order id not null",response.getOrderId());
		assertEquals("Expected Order id 3",order.getOrderId(),response.getOrderId());
		assertEquals("Expected user id user1",order.getUserId(), response.getUserId());
		assertEquals("Expected price 306.0",order.getPrice(), response.getPrice());
		assertEquals("Expected quantity 3.5",order.getQuantity(), response.getQuantity());
		assertEquals("Expected status Active",OrderStatus.ACTIVE, response.getOrderStatus());
		assertEquals("Expected type Sell",ORDER_TYPE_SELL, response.getOrderType());

		order = getOrder("user2", 1.2, 310.0, ORDER_TYPE_SELL);
		response = orderService.registerOrder(order);
		assertNotNull("Expected Order object not null",response);
		assertNotNull("Expected Order id not null",response.getOrderId());
		assertEquals("Expected Order id 4",order.getOrderId(),response.getOrderId());
		assertEquals("Expected user id user2",order.getUserId(), response.getUserId());
		assertEquals("Expected price 310.0",order.getPrice(), response.getPrice());
		assertEquals("Expected quantity 1.2",order.getQuantity(), response.getQuantity());
		assertEquals("Expected status Active",OrderStatus.ACTIVE, response.getOrderStatus());
		assertEquals("Expected type Sell",ORDER_TYPE_SELL, response.getOrderType());


	}

	/**
	 * Cancel a order without order id. Should throw and order not found exception.
	 */
	@Test
	public void testCancelOrderWithoutOrderIdOrderNotFoundException() {
		clearOrderRepository();
		try {
			Order order = getOrder("user1", 4.5, 406.0, ORDER_TYPE_BUY);
			orderService.registerOrder(order);
			Order cancelOrder = new Order();
			// without setting order id!
			orderService.cancelOrder(cancelOrder);
		}catch(Exception e) {
			assertThat(e)
        .isInstanceOf(OrderNotFoundException.class)
        .hasMessage(ORDER_NOT_FOUND_MESSAGE);
		}
	}
	
	/**
	 * Cancel order test
	 * @throws OrderRegisterException
	 * @throws OrderNotFoundException
	 */
	@Test
	public void testCancelOrder() throws OrderRegisterException, OrderNotFoundException {
		clearOrderRepository();
		Order order = getOrder("user1", 4.5, 406.0, ORDER_TYPE_BUY);
		Order regOrder = orderService.registerOrder(order);
		Order response = orderService.cancelOrder(regOrder);
		assertNotNull("Expected Order object not null",response);
		assertNotNull("Expected Order id not null",response.getOrderId());
		assertEquals("Expected Order id 1",order.getOrderId(),response.getOrderId());
		assertEquals("Expected user id user1",order.getUserId(), response.getUserId());
		assertEquals("Expected price 406.0",order.getPrice(), response.getPrice());
		assertEquals("Expected quantity 4.5",order.getQuantity(), response.getQuantity());
		assertEquals("Expected status Canceled",OrderStatus.CANCELED, response.getOrderStatus());
		assertEquals("Expected type Buy",ORDER_TYPE_BUY, response.getOrderType());
		
		List<String> buyOrderSummaryList = orderService.getSummary(ORDER_TYPE_BUY);

		assertTrue("Expected buyOrderSummaryList content to be empty",buyOrderSummaryList.size() == 0);

		
	}
	
	/**
	 * Register few orders and cancel one. Test if the canceled one it excluded in the summary calculation.
	 * @throws OrderRegisterException
	 * @throws OrderNotFoundException
	 */
	@Test
	public void testCancelSummaryCalcOrder() throws OrderRegisterException, OrderNotFoundException {
		clearOrderRepository();
		registerBuyOrders();
		Order cancelOder = new Order();
		cancelOder.setOrderId(4l);
		orderService.cancelOrder(cancelOder);
		
		List<String> buyOrderSummaryList = orderService.getSummary(ORDER_TYPE_BUY);
		
		assertNotNull("Expected buyOrderSummaryList not null",buyOrderSummaryList);
		
		assertTrue("Expected buyOrderSummaryList content to have a value",buyOrderSummaryList.size() > 0);
		
		assertEquals("Expected 2.2 kg for £410.0","2.2 kg for £410.0",buyOrderSummaryList.get(0));
		assertEquals("Expected 2.5 kg for £407.0","2.5 kg for £407.0",buyOrderSummaryList.get(1));
		assertEquals("Expected 4.5 kg for £406.0","4.5 kg for £406.0",buyOrderSummaryList.get(2));

	}

	/**
	 * Order Cache repository test
	 */
	@Test
	public void testGetOrderRepository() {
		assertNotNull("Expected buyOrderSummaryList not null",orderService.getOrderRepository());
	}

	
	/**
	 * Order Id Sequence test
	 */
	@Test
	public void testGetNextOrderIdSequence() {
		orderService.orderIdSequence = 0l;
		assertEquals("Expected sequence value 1",1,orderService.getNextOrderIdSequence());
		
	}
	
	/**
	 * is order valid test. order with mandatory fields.
	 */
	@Test
	public void testIsValid() {
		Order order = getOrder("user1", 4.5, 406.0, "BUY");
		
		assertTrue("Valid order",orderService.isValid(order));
		
	}

	/**
	 * is order valid test. order without mandatory fields.
	 */
	@Test
	public void testIsValidWithoutMandatoryFields() {
		Order order = getOrder(null, null, null, null);
		
		assertFalse(orderService.isValid(order));
		
	}
	
	/**
	 * is order valid test. order without mandatory field userId.
	 */
	@Test
	public void testIsValidWithoutMandatoryFieldUserId() {
		Order order = getOrder(null, 4.5, 406.0, "BUY");
		
		assertFalse(orderService.isValid(order));
		
	}
	
	/**
	 * is order valid test. order without mandatory field quantity.
	 */
	@Test
	public void testIsValidWithoutMandatoryFieldQuantity() {
		Order order = getOrder("user1", null, 406.0, "BUY");
		
		assertFalse(orderService.isValid(order));
		
	}
	
	/**
	 * is order valid test. order without mandatory field price.
	 */
	@Test
	public void testIsValidWithoutMandatoryFieldPrice() {
		Order order = getOrder("user1", 4.5, null, "BUY");
		
		assertFalse(orderService.isValid(order));
		
	}
	
	/**
	 * is order valid test. order without mandatory field orderType.
	 */
	@Test
	public void testIsValidWithoutMandatoryFieldOrderType() {
		Order order = getOrder("user1", 4.5, 406.5, null);
		
		assertFalse(orderService.isValid(order));
		
	}
	
	/**
	 * is order valid test. order with misspelled mandatory field orderType.
	 */
	@Test
	public void testIsValidWithoutMandatoryFieldOrderTypeMisspelled() {
		Order order = getOrder("user1", 4.5, 406.5, "BAYssss");
		
		assertFalse(orderService.isValid(order));
		
	}
	

}
