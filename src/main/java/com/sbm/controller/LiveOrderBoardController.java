/**
 * 
 */
package com.sbm.controller;

import static com.sbm.common.CommonConstants.CANCEL_SUCCESS_MESSAGE;
import static com.sbm.common.CommonConstants.ERROR_STATUS;
import static com.sbm.common.CommonConstants.ORDER_NOT_FOUND_MESSAGE;
import static com.sbm.common.CommonConstants.REGISTER_ORDER_ERROR_MESSAGE;
import static com.sbm.common.CommonConstants.SUCCESS_MESSAGE;
import static com.sbm.common.CommonConstants.SUCCESS_STATUS;
import static com.sbm.common.CommonConstants.UNSUCCESSFUL_MESSAGE;
import static com.sbm.common.CommonConstants.REGISTER_SUCCESS_MESSAGE;
import static com.sbm.common.CommonUtils.getModelFromJson;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sbm.exception.OrderNotFoundException;
import com.sbm.exception.OrderRegisterException;
import com.sbm.model.Order;
import com.sbm.model.OrderSummary;
import com.sbm.service.OrderService;;



/**
 * @author duansubramaniam
 *
 */

@RestController
public class LiveOrderBoardController {
	private static final Logger log = LoggerFactory.getLogger(LiveOrderBoardController.class);
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * @return OrderSummary
	 */
	
	@RequestMapping(value="/getOrdersSummary", method = RequestMethod.GET)
	public OrderSummary getOrdersSummary(){
		OrderSummary orderSummary = new OrderSummary();
		try {
			orderSummary = orderService.getOrdersSummary();
			orderSummary.setStatus(SUCCESS_STATUS);
			orderSummary.setMessage(SUCCESS_MESSAGE);
		}catch (OrderNotFoundException onfe) {
			orderSummary.setStatus(ERROR_STATUS);
			orderSummary.setMessage(ORDER_NOT_FOUND_MESSAGE);
		}catch(Exception e) {
			orderSummary.setStatus(ERROR_STATUS);
			orderSummary.setMessage(UNSUCCESSFUL_MESSAGE);
			log.error("Error during orderService.registerOrder: " + e);
		}
		return orderSummary;
	}
	
	/**
	 * Register Order
	 * @param payload Order as json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value="/registerOrder", method = RequestMethod.POST)
	public Order registerOrder(@RequestBody String payload) throws JsonParseException, JsonMappingException, IOException{
		Order response = new Order();
		try {
			Order order = getModelFromJson(payload,Order.class);
			response = orderService.registerOrder(order);
			response.setStatus(SUCCESS_STATUS);
			response.setMessage(REGISTER_SUCCESS_MESSAGE);
		}catch (OrderRegisterException ore) {
			response.setStatus(ERROR_STATUS);
			response.setMessage(REGISTER_ORDER_ERROR_MESSAGE);
		}catch(Exception e) {
			response.setStatus(ERROR_STATUS);
			response.setMessage(UNSUCCESSFUL_MESSAGE);
			log.error("Error during orderService.registerOrder: " + e);
		}
		return response;
	}
	
	/**
	 * Cancel order
	 * @param payload order to cancel
	 * @return the called order
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value="/cancelOrder", method = RequestMethod.POST)
	public Order cancelOrder(@RequestBody String payload) throws JsonParseException, JsonMappingException, IOException{
		Order response = new Order();
		try {
			Order order = getModelFromJson(payload,Order.class);
			 response = orderService.cancelOrder(order);
			response.setStatus(SUCCESS_STATUS);
			response.setMessage(CANCEL_SUCCESS_MESSAGE);
		}catch (OrderNotFoundException onfe) {
			response.setStatus(ERROR_STATUS);
			response.setMessage(ORDER_NOT_FOUND_MESSAGE);
		}catch(Exception e) {
			response.setStatus(ERROR_STATUS);
			response.setMessage(UNSUCCESSFUL_MESSAGE);
			log.error("Error during orderService.registerOrder: " + e);
		}
		return response;
	}
}
