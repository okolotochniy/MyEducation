package aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NewLoggingAdvice {
    @Around("execution(public String returnBook())")
    public Object aroundLogginingAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("aroundLogginingAspect: В библиотеку пытаються вернуть книгу");

        long begin = System.currentTimeMillis();
        Object targetMethodResult;
        try {
            targetMethodResult = proceedingJoinPoint.proceed();
        } catch (Exception e){
            System.out.println("aroundLogginingAspect: было залогированно исключение " + e);
            throw e;
        }


        long end = System.currentTimeMillis();

        System.out.println("aroundLogginingAspect: В библиотеку вернули книгу");

        System.out.println("Время работы returnBook: " + (end - begin) + " миллисекунд");

        return targetMethodResult;
    }
}
