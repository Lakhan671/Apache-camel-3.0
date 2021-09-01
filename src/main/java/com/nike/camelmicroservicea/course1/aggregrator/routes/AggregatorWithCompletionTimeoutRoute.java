package com.nike.camelmicroservicea.course1.aggregrator.routes;

import com.nike.camelmicroservicea.course1.aggregrator.aggregator.AggregatorSimpleRouteStrategy;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class AggregatorWithCompletionTimeoutRoute extends RouteBuilder {
    public void configure() throws Exception {
        from("timer:first-timer")
                .bean(new ListOfStr())
                .split(body())
                .log("Recieved Message is ${body} ")
                .aggregate(constant(true), new AggregatorSimpleRouteStrategy()).completionSize(2).completionTimeout(3000)
                .log("Aggregated Message is ${body}");

    }
}
