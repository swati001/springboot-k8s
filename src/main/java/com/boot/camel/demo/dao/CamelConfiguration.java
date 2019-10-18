//package com.boot.camel.demo.dao;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.apache.camel.component.servlet.CamelHttpTransportServlet;
//
//
//@Configuration
//public class CamelConfiguration {
//
//	@Value("${api.path}")
//    String contextPath;
//
//    @Bean
//    ServletRegistrationBean servletRegistrationBean() {
//    	ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), contextPath+"/*");
//        servlet.setName("CamelServlet");
//        return servlet;
//    }
//}
