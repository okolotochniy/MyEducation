package aop.aspects;

import aop.Student;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class UniversityLoggingAspect {
    @Before("execution(* getStudents())")
    public void beforeGetStudentsLoggingAdvice() {
        System.out.println("beforeGetStudentsLoggingAdvice: логируем получение списка студентов" +
                "перед методом getStudents");
    }

/*    @AfterReturning(pointcut = "execution(* getStudents())", returning = "students")
    public void afterReturningGetStudentsLoggingAdvice(List<Student> students) {
        Student firstStudent = students.get(0);
        String nameSunameStudent = firstStudent.getNameSurname();
        nameSunameStudent = "MR. " + firstStudent.getNameSurname();
        firstStudent.setNameSurname(nameSunameStudent);


        System.out.println("afterReturningGetStudentsLoggingAdvice: логируем получение списка студентов" +
                " после выполнения метода getStudents");
    }*/
/*    @AfterThrowing(pointcut = "execution(* getStudents())", throwing = "exeption")
    public void afterThrowingReturningGetStudentsLoggingAdvice(Throwable exeption) {
        System.out.println("afterThrowingReturningGetStudentsLoggingAdvice: Исключение " + exeption);
    }*/

    @After("execution(* getStudents())")
    public void afterGetStudentLoggingAdvice() {
        System.out.println("afterGetStudentLoggingAdvice: Логируем нормальное завершение метода getStudents" +
                " или выброс исключения");
    }

}
