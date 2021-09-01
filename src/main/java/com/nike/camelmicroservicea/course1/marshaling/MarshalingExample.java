package com.nike.camelmicroservicea.course1.marshaling;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.apache.camel.dataformat.xstream.XStreamDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Component
public class MarshalingExample extends RouteBuilder {
    @Autowired
    private GetStringData getStringData;
    @Override
    public void configure() throws Exception {
      XStreamDataFormat xStreamDataFormat=new XStreamDataFormat();
      Map<String,String>alias=new HashMap<>();
       alias.put("employee",Emp.class.getName());
       xStreamDataFormat.setAliases(alias);
       xStreamDataFormat.setPermissions(Emp.class.getName());
        from("timer: initial//start?period=5000")
                //.autoStartup(true)
                .routeId("hellowordRoute")
                .bean(getStringData,"getStringEmp")
                 .process(new CustomProcessor())
                //java to xml
                .marshal(pupulateStreamDef())
                 //.marshal().xstream()
                .log("emp xml : ${body}")


                //xml to java
                 .unmarshal(xStreamDataFormat)
               .log("emp java object : ${body}")
                //java to json
                .marshal(new JacksonDataFormat(Emp.class))
                .log("emp Json object  : ${body}")
                //Josn to xml
                .unmarshal(getJSONToXmlFormator())
                .log("emp xml object  : ${body}")
                //json to java

               // .unmarshal(new JacksonDataFormat(Emp.class))
                //.log("emp java object  : ${body}")
        .to("log:?level=INFO&showBody=true");
    }
    private XStreamDataFormat pupulateStreamDef(){
        XStreamDataFormat xStreamDataFormat=new XStreamDataFormat();
        Map<String,String> alias=new HashMap<>();
        alias.put("employee",Emp.class.getName());
        xStreamDataFormat.setAliases(alias);
        return xStreamDataFormat;
    }

    private XmlJsonDataFormat getJSONToXmlFormator(){
         XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setRootName("employee");
        return xmlJsonFormat;
    }
}
//@Component
class GetStringData{
    public String getStringEmp(){
        return "Lakhan Singh,29,01-02-1991";
    }
}