package com.nike.camelmicroservicea.course1.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class CopyFileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
           from("file:data/input?noop=true")
                 //  .routeId("File copy")
                   .routeDescription("copy the file data")
                   .log("copy the file from input to putput folder")
                   .to("log:?level=INFO&showBody=true&showHeaders=true")
                   .to("file:data/output")
                 .to("file:data/output2");
    }
}
