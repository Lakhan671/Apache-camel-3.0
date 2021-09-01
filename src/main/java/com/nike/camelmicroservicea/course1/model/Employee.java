package com.nike.camelmicroservicea.course1.model;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",",skipFirstLine =true,generateHeaderColumns = true)
public class Employee {
    @DataField(pos = 1)
    private String id;
    @DataField(pos = 2)
    private String lName;
    @DataField(pos = 3)
    private String fName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", lName='" + lName + '\'' +
                ", fName='" + fName + '\'' +
                '}';
    }
}
