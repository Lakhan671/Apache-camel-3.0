package com.nike.camelmicroservicea.course1.bean;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.bean.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class CamelBeanExample  extends RouteBuilder {
    @Autowired
    private BeanExample beanExample;
    @Override
    public void configure() throws Exception {
        from("timer:first-timer")
                .routeId("test-1")
                .bean(beanExample,"getCountryNames")
                .split(body())

                .bean(beanExample,"printCountryNames")
                .to("log:first-timer ${body}");
    }
}
//@Component
class BeanExample{
    public String getName(){
        return "my name is Lakhan Singh";
    }

    public String getName2(){
        return "my name is Kunal Singh";
    }

    public void printCountryNames(String name){
        System.out.println("Country name is : "+ name);
    }

    public List<String> getCountryNames() {
        List<String>cNames=new ArrayList<>();
        cNames.add("India");
        cNames.add("USA");
        cNames.add("UK");
        cNames.add("Japan");
        return cNames;

    }
}
