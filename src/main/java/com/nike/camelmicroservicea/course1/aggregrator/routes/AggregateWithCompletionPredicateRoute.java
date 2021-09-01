package com.nike.camelmicroservicea.course1.aggregrator.routes;


import com.nike.camelmicroservicea.course1.aggregrator.aggregator.AggregatorPredicateStrategy;
import com.nike.camelmicroservicea.course1.aggregrator.domain.Order;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.gson.GsonDataFormat;


public class AggregateWithCompletionPredicateRoute extends RouteBuilder{
    public void configure() throws Exception {

        GsonDataFormat dataFormat = new GsonDataFormat(Order.class);
        from("direct:completionPredicate")
                .log("Recieved Message is ${body} and key ${header.aggregatorId}")
                .aggregate(header("aggregatorId"), new AggregatorPredicateStrategy())
                .log(" Message after Aggregation Strategy is ${body} and key ${header.aggregatorId}")
                .completionPredicate(body().contains("order-confirm")).eagerCheckCompletion()
                .log("Final Message is : ${body} ")
                .to("mock:output");
    }
}
