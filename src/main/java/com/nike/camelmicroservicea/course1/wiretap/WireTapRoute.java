package com.nike.camelmicroservicea.course1.wiretap;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class WireTapRoute extends RouteBuilder {

    public void configure() throws Exception {
        from("file:data/input?noop=true")
                .wireTap("file:data/debug")
                .to("file:all");
    }
}
