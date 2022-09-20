package com.zaurtregulov.spring.mvc;

import com.zaurtregulov.spring.mvc.validation.CheckEmail;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.Map;

public class Employee {
    @Size(min = 2, message = "Минимальная длинна 2 символа!")
    private String name;
    @NotBlank(message = "Поле обязательное для заполнения!")
    private String surname;
    @Min(value = 500, message = "Минимальное значение 500")
    @Max(value = 1000, message = "Максимальное значение 1000")
    private int salary;
    private String department;
    private Map<String, String> departments;
    private String carBrand;
    private Map<String, String> carBrands;
    private String[] languages;
    private Map<String, String> languageList;
    @Pattern(regexp = "\\d{3}-\\d{2}-\\d{2}", message = "Введите номер по шаблону XXX-XX-XX")
    private String phoneNumber;
    @CheckEmail(value = "abc.ru", message = "email должен оканчиваться на abc.ru")
    private String email;

    public Employee() {
        departments = new HashMap<>();
        departments.put("ИТ", "Информационные технологии");
        departments.put("ОК", "Отдел кадров");
        departments.put("Бухгалтерия", "Финансовый");

        carBrands = new HashMap<>();
        carBrands.put("BMW", "BMW");
        carBrands.put("Mercedes", "Mercedes");
        carBrands.put("Audi", "Audi");

        languageList = new HashMap<>();
        languageList.put("English", "En");
        languageList.put("French", "Fr");
        languageList.put("Deutsch", "De");
    }

//    public Employee() {
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Map<String, String> getDepartments() {
        return departments;
    }

    public void setDepartments(Map<String, String> departments) {
        this.departments = departments;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Map<String, String> getCarBrands() {
        return carBrands;
    }

    public void setCarBrands(Map<String, String> carBrands) {
        this.carBrands = carBrands;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public Map<String, String> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(Map<String, String> languageList) {
        this.languageList = languageList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }

}
