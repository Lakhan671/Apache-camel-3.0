package com.nike.camelmicroservicea.course1.multicast;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MultiCastRoute extends RouteBuilder {

    public void configure() throws Exception {

       /* from("file:input?noop=true").multicast()
                .to("file:output1", "file:output2");*/

        //parallel Processing

        /*from("file:input?noop=true").multicast().parallelProcessing()
                .to("file:output1", "file:output2");*/

        //Exception Handling
        from("file:data/input?noop=true").multicast().stopOnException()
                .parallelProcessing()
                .to("file:data/output1", "file:data/output2");

    }
}
