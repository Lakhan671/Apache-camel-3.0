package com.nike.camelmicroservicea.course1.marshaling;

import org.apache.camel.Exchange;

import java.util.StringTokenizer;

public class CustomProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String neB=exchange.getIn().getBody(String.class);
        StringTokenizer tokenizer=new StringTokenizer(neB,",");
        Emp emp=new Emp();
        while (tokenizer.hasMoreElements()){

            emp.setName((String) tokenizer.nextElement());
            emp.setAge((String) tokenizer.nextElement());
            emp.setDob((String) tokenizer.nextElement());;
        }
      exchange.getIn().setBody(emp);
    }
}
