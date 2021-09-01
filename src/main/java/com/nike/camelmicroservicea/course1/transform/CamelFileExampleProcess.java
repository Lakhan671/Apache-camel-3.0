package com.nike.camelmicroservicea.course1.transform;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CamelFileExampleProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Exchange the body is:"+exchange.getIn().getBody());
        GenericFile<File> files= (GenericFile<File>) exchange.getIn().getBody();
        String readLine=null;
        String newValue="";
        if(files!=null){
            FileReader file1=new FileReader(files.getFile());
            BufferedReader reader=new BufferedReader(file1);
            while((readLine=reader.readLine())!=null){
                System.out.println("Read line is :"+ readLine);
                String oldValue=readLine;
                newValue=newValue.concat(oldValue.replace(",",":")).concat("\n");
                exchange.getIn().setBody(newValue);
            }
        }
    }
}
