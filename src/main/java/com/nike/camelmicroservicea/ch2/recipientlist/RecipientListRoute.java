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

package com.nike.camelmicroservicea.ch2.recipientlist;

import org.apache.camel.builder.RouteBuilder;

/**
 * Simple RecipientList example.
 */
public class RecipientListRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
            .setHeader("endpointsToBeTriggered")
                .method(MessageRouter.class, "getEndpointsToRouteMessageTo")
            .recipientList(header("endpointsToBeTriggered"));

        from("direct:order.priority").to("mock:order.priority");
        from("direct:order.normal").to("mock:order.normal");
        from("direct:billing").to("mock:billing");
        from("direct:unrecognized").to("mock:unrecognized");
    }
}
