package com.boot.camel.demo.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

import com.boot.camel.demo.model.User;

@Component
public class UserAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }
        List<User> firstResult = oldExchange.getIn().getBody(List.class);
        List<User> secondResult = newExchange.getIn().getBody(List.class);
        oldExchange.getIn().setBody(Stream.concat(firstResult.stream(), secondResult.stream()).distinct().collect(Collectors.toList()));
        return oldExchange;
    }

}
