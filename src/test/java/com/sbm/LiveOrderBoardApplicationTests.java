package com.sbm;

import static com.sbm.common.CommonConstants.ORDER_TYPE_BUY;
import static com.sbm.common.CommonConstants.ORDER_TYPE_SELL;
import static com.sbm.common.CommonUtils.getModelFromJson;
import static com.sbm.common.CommonUtils.mapToJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sbm.common.OrderStatus;
import com.sbm.model.Order;
import com.sbm.model.OrderSummary;
import com.sbm.service.OrderService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class LiveOrderBoardApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	
	
	@MockBean
	OrderService orderService;
	
	String registerOrderUri = "/registerOrder";
	String cancelOrderUri = "/cancelOrder";
	String getOrdersSummaryUri = "/getOrdersSummary";
	
	@Before
	public void Setup() {
		
	}
	
	private MvcResult getPOSTMvcResult(String uri, String inputJson) throws Exception {
		return mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();
	}
	
	private MvcResult getGETMvcResult(String uri) throws Exception {
		
		return mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
	}

	@Test
	public void registerSellOrder() throws Exception {
		Order order = new Order(1l, "user1", 3.5, 306.00, ORDER_TYPE_SELL,
				OrderStatus.ACTIVE);
		
		when(orderService.registerOrder(order)).thenReturn(order);
        String inputJson = mapToJson(order);

        MvcResult mvcResult = getPOSTMvcResult(registerOrderUri,inputJson);

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
        
        Order responseOrder = getModelFromJson(content, Order.class);
    	
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
	public void registerBuyOrder() throws Exception {
		Order order = new Order(1l, "user1", 3.5, 306.00, ORDER_TYPE_BUY,
				OrderStatus.ACTIVE);
		
		when(orderService.registerOrder(order)).thenReturn(order);
        String inputJson = mapToJson(order);
        
        System.out.println("inputJson: " + inputJson);

        MvcResult mvcResult = getPOSTMvcResult(registerOrderUri,inputJson);

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
        
        Order responseOrder = getModelFromJson(content, Order.class);
    	
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
	public void cancelOrder() throws Exception {
		Order order = new Order(1l, "user1", 3.5, 306.00, ORDER_TYPE_BUY,
				OrderStatus.CANCELED);
		
		when(orderService.cancelOrder(order)).thenReturn(order);
        String inputJson = mapToJson(order);

        MvcResult mvcResult = getPOSTMvcResult(cancelOrderUri,inputJson);

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
        
        Order responseOrder = getModelFromJson(content, Order.class);
    	
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
	public void getOrdersSummary() throws Exception {
		
		
		List<String> summaryList = Arrays.asList("5.5 kg for £306.00", "1.5 kg for £307.00");
		Map<String,List<String>> summaryMap = new HashMap<String,List<String>>();
		summaryMap.put(ORDER_TYPE_BUY, summaryList);
		OrderSummary orderSummary = new OrderSummary(summaryMap);
		
		when(orderService.getOrdersSummary()).thenReturn(orderSummary);
        

        MvcResult mvcResult = getGETMvcResult(getOrdersSummaryUri);

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();

        assertEquals("Expected HTTP status 200", 200, status);
        
        
        OrderSummary responseOrderSummary = getModelFromJson(content, OrderSummary.class);
    	
		assertNotNull("Expected OrderSummary object not null",responseOrderSummary);
		assertNotNull("Expected orderSummary content not null",responseOrderSummary.getOrderSummary());
		
		assertTrue("Expected orderSummary content to have a value",responseOrderSummary.getOrderSummary().size() > 0);
		
	}

}
