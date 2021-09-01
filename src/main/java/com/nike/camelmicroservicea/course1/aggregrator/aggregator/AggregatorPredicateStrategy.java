package com.nike.camelmicroservicea.course1.aggregrator.aggregator;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;


public class AggregatorPredicateStrategy implements AggregationStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if(oldExchange==null){
            return newExchange;

        }else{
            String oldBody = (String) oldExchange.getIn().getBody();
            String newBody = (String) newExchange.getIn().getBody();

            newBody = oldBody.concat(":").concat(newBody);

            newExchange.getIn().setBody(newBody);


            return newExchange;
        }
    }
}
