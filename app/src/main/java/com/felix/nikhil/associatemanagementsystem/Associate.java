package com.felix.nikhil.associatemanagementsystem;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Associate {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String name,mobilenumber,department,salary;

    public Associate(){
        //empty constructer
    }

    public Associate(String name, String mobilenumber, String department, String salary) {
        this.name = name;
        this.mobilenumber = mobilenumber;
        this.department = department;
        this.salary = salary;
    }
}
