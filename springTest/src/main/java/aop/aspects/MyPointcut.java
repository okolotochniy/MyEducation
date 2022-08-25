package aop.aspects;

import org.aspectj.lang.annotation.Pointcut;


public class MyPointcut {
    @Pointcut("execution(* addBook(..))")
    public void allAddMetods() {}
}
