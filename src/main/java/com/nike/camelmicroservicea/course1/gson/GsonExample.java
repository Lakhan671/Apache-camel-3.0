package com.nike.camelmicroservicea.course1.gson;

import com.nike.camelmicroservicea.course1.marshaling.CustomProcessor;
import com.nike.camelmicroservicea.course1.marshaling.Emp;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.gson.GsonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class GsonExample extends RouteBuilder {
    @Autowired
    private EmpGet empGet;
    @Override
    public void configure() throws Exception {
        GsonDataFormat gsonDataFormat=new GsonDataFormat(Emp.class);
        from("timer: initial//start?period=5000")
                .routeId("GsonExample")
                .bean(empGet)
                .marshal(gsonDataFormat)

                .log("Marshal object is : ${body}")
                .unmarshal(gsonDataFormat)
                  .log("unMarshal object is : ${body}");

    }


}
@Component
class EmpGet{
    public Emp getEMp(){
        Emp emp=new Emp();
        emp.setDob("01-02-1991");
        emp.setAge("29");
        emp.setName("Lakhan Singh");
        return emp;
    }
}