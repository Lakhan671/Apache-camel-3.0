/*
 * Copyright (C) Scott Cranton, Jakub Korab, and Christian Posta
 * https://github.com/CamelCookbook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nike.camelmicroservicea.ch2.multicast;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * Multicast example with exceptions handled in the AggregationStrategy.
 */
public class MulticastExceptionHandlingInStrategyRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
            .multicast().aggregationStrategy(new ExceptionHandlingAggregationStrategy())
                .to("direct:first")
                .to("direct:second")
            .end()
            .log("continuing with ${body}")
            .to("mock:afterMulticast")
            .transform(body()); // copy the In message to the Out message; this will become the route response

        from("direct:first")
            .onException(Exception.class)
                .log("Caught exception")
                .to("mock:exceptionHandler")
                .transform(constant("Oops"))
            .end()
            .to("mock:first")
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    throw new IllegalStateException("something went horribly wrong");
                }
            });

        from("direct:second")
            .to("mock:second")
            .transform(constant("All OK here"));
    }
}
