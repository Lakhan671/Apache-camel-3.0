package com.nike.camelmicroservicea.course1.readcsv;

import com.nike.camelmicroservicea.course1.model.Employee;
import org.apache.camel.Exchange;

public class CSVProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody(Employee.class));
    }
}
