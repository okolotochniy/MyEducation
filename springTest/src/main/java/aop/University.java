package aop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class University {

    private List<Student> students = new ArrayList<>();

    public void addStudents() {
        Student st1 = new Student("Dimon Medvedev", 4, 7.2);
        Student st2 = new Student("Volodya Putin", 3, 9.9);
        Student st3 = new Student("Gena Bookin", 5, 6.6);

        students.add(st1);
        students.add(st2);
        students.add(st3);
    }

    public List<Student> getStudents() {
        System.out.println("Начало метода getStudents");
        System.out.println(students.get(3));
        System.out.println("Information of method getStudents:");
        System.out.println(students);
        return students;
    }
}
