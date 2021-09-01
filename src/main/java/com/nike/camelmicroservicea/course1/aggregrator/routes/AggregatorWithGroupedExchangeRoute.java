package com.nike.camelmicroservicea.course1.aggregrator.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;
import org.springframework.stereotype.Component;

//@Component
public class AggregatorWithGroupedExchangeRoute extends RouteBuilder{

    public void configure() throws Exception {

        from("timer:first-timer")
                .bean(new ListOfStr())
                .split(body())
                .log("Received Message is ${body}  after split")
                .aggregate(constant(true), new GroupedExchangeAggregationStrategy()).completionSize(3)
                .log("Aggregated Message is ${body} after joined");



    }
}
