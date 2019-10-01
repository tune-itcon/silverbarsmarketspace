/**
 * 
 */
package com.sbm.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Duan
 *
 */
public class CommonUtils {
	
	public static boolean isNotNullAndNotEmpty(String srcValue){
		return (srcValue != null && srcValue.trim().length() > 0) ? true : false;
	}
	
	/**
	 * Converts json string to any class type object
	 * @param <T>
	 * @param payload
	 * @param model
	 * @return 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public final static <T> T getModelFromJson(String payload, Class<T>  model) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(payload, model);
	}
	
	/*
	 * Converts Object to json string
	 */
	public final static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
