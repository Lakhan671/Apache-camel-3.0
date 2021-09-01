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

package com.nike.camelmicroservicea.ch1.org.camelcookbook.structuringroutes.propertyplaceholder;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Route that demonstrates the use of properties in Java.
 */
//@Component
public class PropertyConsumingRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("{{start.endpoint}}")
            .transform().simple("{{transform.message}}: ${body}")
            .log("Set message to ${body}")
            .to("{{end.endpoint}}");
    }
}
