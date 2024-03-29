package org.example.spring_introduction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test6 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Person person = context.getBean("personBean", Person.class);
        person.callYourPet();
        System.out.println(person.getSurname());
        System.out.println(person.getAge());

/*        Pet cat1 = context.getBean("catBean", Cat.class);
        Pet cat2 = context.getBean("catBean", Cat.class);

        System.out.println(cat1);
        System.out.println(cat2);
        System.out.println(cat1==cat2);*/

        context.close();
    }
}
