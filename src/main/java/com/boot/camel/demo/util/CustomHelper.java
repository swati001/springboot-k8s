package com.boot.camel.demo.util;

import com.boot.camel.demo.model.UserResponse;

public class CustomHelper {
	
	 public static <T> UserResponse<T> createResponse(T obj, String message, String code){
	        UserResponse<T> response = new UserResponse<T>();
	        response.setResponseObject(obj);
	        response.setResponseCode(code);
	        response.setResponseMessage(message);
	        return response;
	    }

}
