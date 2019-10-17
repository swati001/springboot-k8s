package com.boot.camel.demo.util;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.boot.camel.demo.model.User;
import com.boot.camel.demo.model.UserResponse;
import com.boot.camel.demo.repository.UserRepository;

import static com.boot.camel.demo.util.CustomHelper.createResponse;

public class UserProcessor implements Processor {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void process(Exchange exchange) throws Exception {

        String firstName = (String) exchange.getIn().getHeader("firstName");
        String lastName = (String) exchange.getIn().getHeader("lastName");

        List<User> people = null;

        if(StringUtils.isNotBlank(firstName)) {
            people =  userRepository.findByFirstName(firstName);
        } else if(StringUtils.isNotBlank(lastName)) {
            people = userRepository.findByLastName(lastName);
        } else {
            people = userRepository.findAll();
        }

        exchange.getIn().setBody(people);
    }

    public UserResponse<User> insertPerson(Exchange exchange) {
        User user = userRepository.insert(exchange.getIn().getBody(User.class));
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "201");
        return createResponse(user, "Successful creation", "201");
    }

    public User getPerson(@Header("id") String id) {
        return userRepository.findOne(id);
    }

    public List<User> getPeopleByFirstName(@Header("firstName") String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public List<User> getPeopleByLastName(@Header("lastName") String lastName) {
        return userRepository.findByLastName(lastName);
    }

}
