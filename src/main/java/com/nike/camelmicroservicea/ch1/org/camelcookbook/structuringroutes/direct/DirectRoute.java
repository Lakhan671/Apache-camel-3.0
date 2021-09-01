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

package com.nike.camelmicroservicea.ch1.org.camelcookbook.structuringroutes.direct;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class DirectRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:A")
            .transform(simple("A1[ ${body} ]"))
            .to("direct:B")
            .transform(simple("A2[ ${body} ]"))
            .to("mock:endA");

        from("direct:B")
            .transform(simple("B[ ${body} ]"))
            .to("mock:endB");
    }
}
