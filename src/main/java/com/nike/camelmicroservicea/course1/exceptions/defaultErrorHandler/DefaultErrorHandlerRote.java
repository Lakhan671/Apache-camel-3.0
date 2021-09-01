package com.nike.camelmicroservicea.course1.exceptions.defaultErrorHandler;


import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

//@Component
public class DefaultErrorHandlerRote  extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler().maximumRedeliveries(3).redeliveryDelay(5000).backOffMultiplier(2).retryAttemptedLogLevel(LoggingLevel.WARN));
        from("timer:first-timer")
                .routeId("testException-1")
                .bean(new DataModifier(),"map")
                .log("log:level=INFO&shpwBody=true")
                 .to("mock:errorqueue");
    }
}

class DataModifier{
    Logger log=Logger.getLogger(DataModifier.class.getName());
    public String map(String input){
        String newBody=null;

        try {
            newBody = input.replace(",", ":");
        }catch (RuntimeException r){
            log.severe("Runtime Exception: "+r);
            throw r;
        }catch (Exception e){
            log.severe("Exception: "+e);
            throw e;
        }
return newBody;
    }
}