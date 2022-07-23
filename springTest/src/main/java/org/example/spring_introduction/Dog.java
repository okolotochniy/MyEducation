package org.example.spring_introduction;

import org.springframework.stereotype.Component;

//@Component("dogBean")
public class Dog implements Pet {

    private String dogName;
    public Dog() {
        System.out.println("Dog bean is created");
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public void initMethod() {
        System.out.println("Init method is run");
        System.out.println(getDogName());
    }
    public void destroyMethod() {
        System.out.println("Destroy method is run");

    }

    @Override
    public void say () {
        System.out.println("Bow Wow");
    }
}
