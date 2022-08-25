package aop.aspects;

import aop.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Component
@Aspect
@Order(1)
public class LoggingAspect {

/*    @Pointcut("execution(* aop.UniLibrary.get*())")
    private void allGetMetodsFromUnilibrary(){}

    @Pointcut("execution(* aop.UniLibrary.return*())")
    private void allReturnMetodsFromUnilibrary(){}*/

    /*@Pointcut("execution(* aop.UniLibrary.*(..))")
    private void allGetAndReturnMetodsFromUnilibrary(){}

    @Pointcut("execution(* aop.UniLibrary.getMagazine())")
    private void getMagazineMetodsFromUnilibrary(){}

    @Pointcut("allGetAndReturnMetodsFromUnilibrary() && !getMagazineMetodsFromUnilibrary()")
    private void allGetAndReturnMetodsExeptMagazine(){}

    @Before("allGetAndReturnMetodsExeptMagazine()")
    public void beforeGetAndReturnMetodsExeptMagazine(){
        System.out.println("beforeGetAndReturnMetodsExeptMagazine: Log");
    }*/

/*    @Pointcut("allGetMetodsFromUnilibrary() || allReturnMetodsFromUnilibrary()")
    private void allGetAndReturnMetodsFromUnilibrary(){}


    @Before("allGetMetodsFromUnilibrary()")
    public void beforeGetLoggingAdvice(){
        System.out.println("beforeGetLoggingAdvice: writing Log #1");
    }

    @Before("allReturnMetodsFromUnilibrary()")
    public void beforeReturnLoggingAdvice(){
        System.out.println("beforeReturnLoggingAdvice: writing Log #2");
    }

    @Before("allGetAndReturnMetodsFromUnilibrary()")
    public void beforeGetAndReturnLoggingAdvice(){
        System.out.println("beforeGetAndReturnLoggingAdvice: writing Log #3");
        }*/




    @Before("aop.aspects.MyPointcut.allAddMetods()")
    public void beforeAddloggingAdvice(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("methodSignature = " + methodSignature);
        System.out.println("methodSignature.getMethod() = " + methodSignature.getMethod());
        System.out.println("methodSignature.getReturnType() = " + methodSignature.getReturnType());
        System.out.println("methodSignature.getName() = " + methodSignature.getName());
        System.out.println("----------------------------------------------------------");

        if (methodSignature.getName().equals("addBook")){
            Object[] arguments = joinPoint.getArgs();
            for (Object obj:arguments) {
                if (obj instanceof Book) {
                    Book myBook = (Book) obj;
                    System.out.println("Название книги - " + myBook.getBookName());
                    System.out.println("Автор книги - " + myBook.getAuthor());
                    System.out.println("Год издания - " + myBook.getYearOfWriting());
                }
                else if (obj instanceof String) {
                    System.out.println("Книгу добавил - " + obj);
                }
            }
        }
        System.out.println("----------------------------------------------------------");

        System.out.println("beforeGetloggingAdvice логирование: попытки получить книгу/журнал");
        System.out.println("----------------------------------------------------------");
    }

}
