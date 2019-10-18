package com.boot.camel.demo.route;

import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.boot.camel.demo.model.User;
import com.boot.camel.demo.util.UserAggregationStrategy;
import com.boot.camel.demo.util.UserProcessor;

@Component
public class ApacheCamelRoute extends ExceptionRoute {

	@Value("${server.port}")
	String serverPort;

	@Value("${api.path}")
	String contextPath;

	@Autowired
	private UserProcessor userProcessor;

	@Autowired
	private UserAggregationStrategy userAggregationStrategy;

	@Override
	public void configure() throws Exception {
		super.configure();

		restConfiguration().contextPath(contextPath).port(serverPort).enableCORS(true).apiContextPath("/api-doc")
				.apiProperty("api.title", "REST API").apiProperty("api.version", "v1").apiProperty("cors", "true") // cross-site
				.apiContextRouteId("doc-api").component("servlet").bindingMode(RestBindingMode.json)
				.dataFormatProperty("prettyPrint", "true");

		rest("/user").id("rest-user").get("").id("rest-user-get").consumes("application/json")
				.produces("application/json").to("direct:getAllUser").get("/{id}").id("rest-user-get-id")
				.consumes("application/json").produces("application/json").to("direct:getSingleUser").post("")
				.id("rest-user-post").consumes("application/json").produces("application/json").type(User.class)
				.to("direct:postUser");

		from("direct:getAlluser").id("direct-getAlluser").to("log:DEBUG?showBody=true&showHeaders=true")
				.process(userProcessor);

		from("direct:getSingleuser").id("direct-getSingleuser").to("log:DEBUG?showBody=true&showHeaders=true")
				.bean(userProcessor, "getUser");

		from("direct:postuser").id("direct-postuser").to("log:DEBUG?showBody=true&showHeaders=true").bean(userProcessor,
				"insertUser");

	}
}
