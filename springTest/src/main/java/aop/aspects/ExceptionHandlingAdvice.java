package aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class ExceptionHandlingAdvice {
    @Before("aop.aspects.MyPointcut.allAddMetods()")
    public void beforeAddExceptionHandlingAdvice() {
        System.out.println("beforeExceptionHandlingAdvice: ловим исключение при попытки получить" +
                "книгу/журнал");
        System.out.println("----------------------------------------------------------");
    }
}
