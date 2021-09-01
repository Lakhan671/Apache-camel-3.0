package com.nike.camelmicroservicea.course1.recipientlisteip;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RecipientListRoute extends RouteBuilder {

    public void configure() throws Exception {

        from("file:data/xmlinput?noop=true")
                .setHeader("type", xpath("/employee/@type"))
                .process(new RecipientEIPProcessor())
                .recipientList(header("type"));

    }
}
