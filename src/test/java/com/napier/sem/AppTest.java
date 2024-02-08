package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printSalariesTestNull()
    {
        app.printSalaries(null);
    }
    @Test
    void printSalariesTestEmpty(){
        ArrayList<Employee> employess = new ArrayList<Employee>();
        app.printSalaries(employess);
    }
    @Test
    void printSalariesTestContainsNull() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(null);
        app.printSalaries(employees);
    }
    @Test
    void printSalaries()
    {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";
        emp.title = "Engineer";
        emp.salary = 55000;
        employees.add(emp);
        app.printSalaries(employees);
    }
    @Test
    void displayEmployeeTestNull(){
        app.displayEmployee(null);
    }
    @Test
    void displayEmployeeTestEmpty(){
        Employee emp = new Employee();
        emp.emp_no= 255530;
        emp.first_name = "Ronghao";
        emp.last_name = "Garigliano";
        emp.title = null;
        emp.salary = 0;
        emp.dept = null;
        emp.manager= null;
        app.displayEmployee(emp);
    }
//    @Test
//    void displayEmployeeTest(){

//    }

}