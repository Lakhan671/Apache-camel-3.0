package com.nike.camelmicroservicea.course1.readcsv;

import com.nike.camelmicroservicea.course1.model.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

//@Component
public class CsvunmarshalRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        DataFormat bindy=new BindyCsvDataFormat(Employee.class);
        from("file:data/csv/input?fileName=csv.txt&noop=true")
                .routeId("csvroute")
                .unmarshal(bindy)
                .log("unmarshal msg is  ${body}")
                .process(new CSVProcessor())
                .marshal(bindy)
                .to("file:data/csv/output?fileName=csvo.txt");
    }
}
