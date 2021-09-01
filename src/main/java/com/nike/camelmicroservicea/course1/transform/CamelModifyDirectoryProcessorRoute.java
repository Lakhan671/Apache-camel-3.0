package com.nike.camelmicroservicea.course1.transform;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class CamelModifyDirectoryProcessorRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
       from("direct:processorInput")
               .log("Recieved msg is ${body}  and headers are ${headers}")
               .process(new CamelDiectExampleProcessor())
       .log("Recieved msg is ${body}  and headers are ${headers}")
       .to("fileL:data/output2?fileName=output2.txt");
    }
}
