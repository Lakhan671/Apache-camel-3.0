package com.nike.camelmicroservicea.course1.transform;

import org.apache.camel.Exchange;

public class CamelDiectExampleProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        try{
            String str=exchange.getIn().getBody().toString();
              String newVa=str.replace(",",":");
              exchange.getIn().setBody(newVa);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
