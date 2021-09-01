package com.nike.camelmicroservicea;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class TimerRoute extends RouteBuilder {
    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;
    @Autowired
    private SimpleProcessing simpleProcessing;
    @Override
    public void configure() throws Exception {
       from("timer:first-timer")
                .routeId("test-1")
              // .autoStartup(false)
              //  .transform().constant("kjgkjhh")
                //.bean("getCurrentTimeBean")
                .bean(getCurrentTimeBean,"getCurrentTime2")
               .split(simple("${body}"))

                .log("After split---- ${body}")
                .bean(simpleProcessing)
                .log("${body}")
                .process(new SimpleLoggingProcessor())
                .to("log:first-timer");

        from("timer: initial//start?period=5000")
                .autoStartup(false)
                .routeId("hellowordRoute")
                .to("log:hello world form camel");
    }




}
@Component
class GetCurrentTimeBean{
    public Map<String,List<String>> getCurrentTime2(){
        WeakHashMap<String, List<String>>map=new WeakHashMap<>();
        ArrayList<String>list=new ArrayList<>();
        list.add(LocalDateTime.now().toString());
        ArrayList<String>list2=new ArrayList<>();
        list2.add("india");
        list2.add("Japan");
        map.put("current time 2",list);
        map.put("country",list2);
       return map;
    }


    public String getCurrentTime1(){
     return "current time 1  "+ LocalDateTime.now();
      }

}

@Component
class SimpleProcessing{
    private Logger logger= (Logger) LoggerFactory.getLogger(SimpleProcessing.class);

            public void process1(String str){
             logger.info("SimpleProcessing {} ",str);
            }
}
class SimpleLoggingProcessor implements Processor {
    private Logger logger= (Logger) LoggerFactory.getLogger(SimpleLoggingProcessor.class);
    @Override
    public void process(Exchange exchange) throws Exception {

        logger.info("SimpleLoggingProcessor {} ",exchange.getIn().getBody());
    }
}