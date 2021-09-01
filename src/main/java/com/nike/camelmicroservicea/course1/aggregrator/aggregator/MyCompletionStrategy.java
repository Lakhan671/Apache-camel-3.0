package com.nike.camelmicroservicea.course1.aggregrator.aggregator;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public final class MyCompletionStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            // we start a new correlation group, so complete all previous groups
            newExchange.setProperty(Exchange.AGGREGATION_COMPLETE_ALL_GROUPS, true);
            return newExchange;
        }

        String body1 = oldExchange.getIn().getBody(String.class);
        String body2 = newExchange.getIn().getBody(String.class);

        oldExchange.getIn().setBody(body1 + body2);
        return oldExchange;
    }
}