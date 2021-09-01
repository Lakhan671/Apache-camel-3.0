package com.nike.camelmicroservicea.course1.exceptions.onException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
//@Component
public class OnExceptionHandlerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
       // onException(RuntimeException.class).log(LoggingLevel.WARN, "Exception in Bean caught here");

        //onException(ApplicationException.class).log(LoggingLevel.WARN, "Application Exception in Bean caught here");

        //Multiple Exceptions in same line
        //onException(ApplicationException.class).log(LoggingLevel.WARN, "Application Exception in Bean caught here");

        //  onException(SQLException.class).log(LoggingLevel.WARN, "Exception while connecting to DB");*/

        //With Re-delivery and redleivery delay
        // onException(RuntimeException.class,ApplicationException.class).maximumRedeliveries(2).redeliveryDelay(5000).log(LoggingLevel.WARN, "Exception in Processor caught here");

        //with Redelivery and Handled
       //  onException(RuntimeException.class).handled(true).maximumRedeliveries(2).delay(2000).process(new GenerateErrorResponseProcessor()).log(LoggingLevel.WARN, "Exception in Processor caught here");
         //onException(RuntimeException.class ).handled(true).maximumRedeliveries(2).delay(2000).process(new GenerateErrorResponseProcessor()).to("kafka:errortopic").log(LoggingLevel.WARN, "Exception in Processor caught here");

        //Ignoring Exception:
        //You cant use continued and handled in the same route.
       onException(RuntimeException.class).continued(true).log(LoggingLevel.WARN, "Exception in Processor caught here");
       from("timer:first-timer")
                .routeId("testException-1")
                .bean(new DataModifier2(),"map")
                .log("log:level=INFO&shpwBody=true")
                .to("mock:errorqueue");
    }
}


class DataModifier2{
    Logger log=Logger.getLogger(DataModifier2.class.getName());
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