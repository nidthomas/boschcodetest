package com.bosch.employeeman.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

//@Component
@Entity
//@Table(name= "employee")
// To increase speed and save sql statement execution time.
//@DynamicInsert
//@DynamicUpdate
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated employee ID")
    private int id;
    @ApiModelProperty(notes = "The name of the employee")
    private String name;
    @ApiModelProperty(notes = "The department of the employee")
    private String department;
    @ApiModelProperty(notes = "The salary of the employee")
    private double salary;

    public Employee() { }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary + "]";
    }
}