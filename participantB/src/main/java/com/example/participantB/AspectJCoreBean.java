package com.example.participantB;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author: Halo
 * @date: 2019/5/23 20:55
 * @Description:
 */
@Aspect
@Component
public class AspectJCoreBean {

    @Before("execution(* com.example.participantB.TestAspect.*())")
    public void test (){
        System.out.println("before test");
    }
    @After("execution(* com.example.participantB.TestAspect.*())")
    public void after (){
        System.out.println("After test");
    }

    @AfterReturning("execution(* com.example.participantB.TestAspect.*())")
    public void afterReturning (){
        System.out.println("afterReturning test");
    }

}
