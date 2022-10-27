package com.example.spring_datajpa.controller;

import com.example.spring_datajpa.entity.Employee;
import com.example.spring_datajpa.exception_handling.NoSuchEmployeeException;
import com.example.spring_datajpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            throw new NoSuchEmployeeException("Работника с таким ID: " + id + ", в базе нет!");
        }

        return employee;
    }
    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);

        return employee;

    }
    @PutMapping("employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);

        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            throw new NoSuchEmployeeException("Работника с таким ID: " + id + ", в базе нет!");
        }
        String empToPrint = employee.toString();
        employeeService.deleteEmployee(id);

        return "Employee " + empToPrint + " removed from database.";
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee>showAllEmployeesByName(@PathVariable String name) {
        List<Employee> employeeList = employeeService.findAllByName(name);
        return employeeList;
    }

}
