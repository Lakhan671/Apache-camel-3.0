package com.nike.camelmicroservicea.course1.model;

import org.apache.camel.dataformat.bindy.annotation.BindyConverter;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@FixedLengthRecord(paddingChar = ' ',ignoreTrailingChars = true,length = 18)
public class EmployeeWithFixedLength {
    @DataField(pos = 1, length = 3)
    private String id;
    @DataField(pos = 2,length = 7)
    private String name;
    @DataField(pos = 3,length = 8)
    private String role;
    @DataField(pos = 4,length = 6)
    private String te;

    public String getTe() {
        return te;
    }

    public void setTe(String te) {
        this.te = te;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "EmployeeWithFixedLength{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", te='" + te + '\'' +
                '}';
    }
}
