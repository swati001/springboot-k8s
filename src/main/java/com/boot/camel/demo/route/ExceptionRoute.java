package com.boot.camel.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boot.camel.demo.util.ExceptionProcessor;

@Component
public class ExceptionRoute extends RouteBuilder {

	@Autowired
	private ExceptionProcessor exceptionProcessor;

	@Override
	public void configure() throws Exception {

		onException(Exception.class).handled(true).id("exception-handler").log("Exception: '${exception.stacktrace}'")
				.process(exceptionProcessor).marshal().json(JsonLibrary.Jackson);
	}
}