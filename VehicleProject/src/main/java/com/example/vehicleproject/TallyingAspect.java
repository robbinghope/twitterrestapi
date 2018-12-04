package com.example.vehicleproject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TallyingAspect {
    private int tally = 0;

    @Pointcut("execution(public * com.example..*(..))")
    public void publicMethod() {}

    @After("publicMethod() && @annotation(Tallied)")
    public void addTally(final JoinPoint joinPoint) {
        System.out.println("*** Number of methods executed: " + ++tally);
    }
}
