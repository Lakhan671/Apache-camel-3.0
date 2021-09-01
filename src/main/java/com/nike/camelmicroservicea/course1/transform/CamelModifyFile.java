package com.nike.camelmicroservicea.course1.transform;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class CamelModifyFile extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:data/input?noop=true")
                .log("Read file is ${body} and header are ${headers}")
                .process(new CamelFileExampleProcess())
                .to("file:data/output?fileName=outfile.text");

    }
}
