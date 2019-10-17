package com.boot.camel.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse<T> {
	    private T responseObject;
	    private String responseMessage;
	    private String responseCode;

	    @Override
	    public String toString() {
	        return "Response{" +
	                "responseObject=" + responseObject +
	                ", responseMessage='" + responseMessage + '\'' +
	                ", responseCode='" + responseCode + '\'' +
	                '}';
	    }

}
