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

import org.apache.camel.builder.RouteBuilder;

/**
 * Simple multicast example with parallel processing.
 */
public class MulticastWithAggregationRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
            .multicast().aggregationStrategy(new ConcatenatingAggregationStrategy())
                .to("direct:first")
                .to("direct:second")
            .end()
            .transform(body()); // copy the In message to the Out message; this will become the route response

        from("direct:first")
            .transform(constant("first response"));

        from("direct:second")
            .transform(constant("second response"));
    }
}
