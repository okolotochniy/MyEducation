package org.test.spring.rest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.test.spring.rest.configuration.MyConfig;
import org.test.spring.rest.entity.Employee;

import java.util.List;


public class App
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

//        List<Employee> allEmployees = communication.getAllEmployees();
//        for (Employee employee :allEmployees) {
//            System.out.println(employee);
//        }

//        Employee empById = communication.getEmployee(2);
//        System.out.println(empById);

        Employee employee = new Employee("Gena", "Bukin", "ITTTT", 10);
        employee.setId(10);
        communication.saveEmployee(employee);

//        communication.deleteEmployee(10);
    }
}
