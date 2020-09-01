package com.example.manage_platform.manage.aop;

import com.example.manage_platform.manage.annotation.LoginInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class LoginAop {

    /**
     * @annotation 作用于注解
     * execution()作用于方法或者类
     */
//    @Pointcut("execution(* com.example.manage_platform.manage.controller.*(..)))")
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping))")
    private void anyMethod(){}//定义一个切入点

    @Before("anyMethod()")
    public void getUser1(){
        System.out.println("signature");
    }


    @Around("anyMethod()")
    public Object getUser2(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        LoginInfo annotation = method.getAnnotation(LoginInfo.class);
        if (annotation != null) {
            String value = annotation.value();
        } else {

        }
        Object object = joinPoint.proceed();//执行该方法
        return object;
    }

}
