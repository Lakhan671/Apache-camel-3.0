package com.nike.camelmicroservicea.course1.aggregrator.routes;

import com.nike.camelmicroservicea.course1.aggregrator.aggregator.AggregatorSimpleRouteStrategy;
import com.nike.camelmicroservicea.course1.aggregrator.aggregator.MyCompletionStrategy;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


//@Component
public class AggregatorSimpleRoute extends RouteBuilder{
    @Autowired
private ListOfStr listOfStr;
    public void configure() throws Exception {
        from("timer:first-timer")
                //.log("Recieved Message is ${body} and key ${header.aggregatorId}")
                //.aggregate(header("aggregatorId"), new MyCompletionStrategy())
                .bean(listOfStr)
                .split(body())
                .log("split Message is ${body}")
                .aggregate(constant(true), new AggregatorSimpleRouteStrategy()).completionSize(6)
               .log("Aggregated Message is ${body}");
               // .to("mock:output");



    }

}
//@Component
class ListOfStr{
    public List<String> getStr(){
        List<String>s=new ArrayList<>();
        s.add("1");
        s.add("2");
        s.add("3");
        return s;
    }
}
