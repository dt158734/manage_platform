package com.example.manage_platform.manage.aop;

import com.example.manage_platform.manage.annotation.LoginInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoginAop {

    /**
     * @annotation 作用于注解
     * execution()作用于方法或者类
     */
//    @Pointcut("execution(* com.example.manage_platform.manage.controller.*(..)))")
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping))")
    @Pointcut("@annotation(com.example.manage_platform.manage.annotation.LoginInfo))")
    private void anyMethod(){}//定义一个切入点

    @Before("anyMethod()")
    public void getUser1(){
        System.out.println("signature");
    }


    /**
     * 环绕
     * @param joinPoint aop的结合点
     * @return 返回接口的值
     * @throws Throwable
     */
    @Around("anyMethod()")
    public Object getUser2(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        LoginInfo annotation = method.getAnnotation(LoginInfo.class);
        String value = annotation.value();
        if ("0".equals(value)) {
            return "请在方法" + signature.getMethod() + "设置注解参数";
        }
        // 让其继续执行该方法
        Object object = joinPoint.proceed();
        return object;
    }

}
