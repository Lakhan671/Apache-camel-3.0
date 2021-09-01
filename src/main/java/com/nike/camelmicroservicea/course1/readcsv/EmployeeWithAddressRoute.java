package com.nike.camelmicroservicea.course1.readcsv;

import com.nike.camelmicroservicea.course1.model.Employee;
import com.nike.camelmicroservicea.course1.model.EmployeeWithAddress;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

//@Component
public class EmployeeWithAddressRoute extends RouteBuilder {
    DataFormat bindy=new BindyCsvDataFormat(EmployeeWithAddress.class);
    @Override
    public void configure() throws Exception {
        from("file:data/csv/input/?fileName=filewithaddress.txt&noop=true")
                .unmarshal(bindy)
                .log("unmarshal message is : ${body}")
                .to("log: emp and address is : ${body}")
                .marshal(bindy)
                .to("file:data/csv/output?fileName=filenamewith.txt");
    }
}
