package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee(255530);
        assertEquals(emp.emp_no, 255530);
        assertEquals(emp.first_name, "Ronghao");
        assertEquals(emp.last_name, "Garigliano");
    }
    @Test
    void getAllSalaries()
    {
        ArrayList<Employee> emp = app.getAllSalaries();
        assertEquals(emp.get(0).getEmp_no(), 10001, "employee number 10001");
        assertEquals(emp.get(0).getFirst_name(), "Georgi", "Georgi Firstname of employee number 10001");
        assertEquals(emp.get(0).getLast_name(), "Facello", "Facello Lastname of employee number 10001");
        assertEquals(emp.get(0).getSalary(), 88958, "Salary of employee number 88958");
    }
    @Test
    void getAllSalariesQuariesTest() {
        try {

            Statement stmt = app.con.createStatement();
            String strSelect =
                    "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                            + "FROM employees, salaries "
                            + "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' "
                            + "ORDER BY employees.emp_no ASC";
            ResultSet rset = stmt.executeQuery(strSelect);
            assertNotNull(rset); //to check the rset is not include with null value
            ArrayList<Employee> employees = new ArrayList<Employee>();
            assertTrue(rset.next());
            while (rset.next()) {
                Employee emp = new Employee();
                int testint = rset.getInt("employees.emp_no");
                String testfn = rset.getString("employees.first_name");
                String testln = rset.getString("employees.last_name");
                int testsal = rset.getInt("salaries.salary");
                employees.add(emp);
                assertTrue(testint != -1, "Employee numbers shouldn't be less than 1");
                assertNotNull(testfn, "testing first_name variable not include null value.");
                assertNotNull(testln, "testing last_name variable not include null value");
            }
            assertNotNull(employees);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}