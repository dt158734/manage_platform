package com.example.manage_platform.manage.aop;

import com.example.manage_platform.constant.Constants;
import com.example.manage_platform.constant.DataSourceHolder;
import com.example.manage_platform.manage.enums.WriteMethodsEnum;
import com.example.manage_platform.utils.RedisUtil;
import com.example.manage_platform.utils.SqlUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by haiSheng on 2020/4/20.
 */
@Order(1)
@Component
@Aspect
@Log4j2
public class DataSourceSpilteAspect {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private RedisUtil redisUtil;

//    @Pointcut("execution(* com..dao..*(..))")
    @Pointcut("execution(* com.example.manage_platform.manage.dao..*(..))")
    public void mobileDataSourcePointcut(){
    }

//    @Around("mobileDataSourcePointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = null;
        try {
//            System.out.println("++++++++++切源开始+++++++++++");

            //1.从redis中获取主数据库，若获取不到直接退出，否则判断当前数据源是会否为主，若不为主，则切换到主数据源
            //2.调用目标方法
            String sql = SqlUtils.getMybatisSql(pjp, sqlSessionFactory);
            Signature signature = pjp.getSignature();
            String name = signature.getDeclaringTypeName() + "." + signature.getName();
            List<String> allWriteMethods = WriteMethodsEnum.getAllWriteMethods();
            String firstStatement = sql.substring(0, 6);
            if (firstStatement.equalsIgnoreCase(Constants.SELECT) && (! allWriteMethods.contains(name))) {
                DataSourceHolder.setCustomeType(Constants.READDATASOURCE);
            }else{
                DataSourceHolder.setCustomeType(Constants.WRITEDATASOURCE);
            }
            //3.获取SQL
            System.out.println("######################"+sql+"######################");
            proceed = pjp.proceed();
//            System.out.println("数据源："+DataSourceHolder.getCustomeType());
            DataSourceHolder.remove();
        }catch (Exception e){
            System.out.println("数据源："+DataSourceHolder.getCustomeType());
            DataSourceHolder.remove();
            log.error("读写分离异常", e);
        }
//        System.out.println("++++++++++切源结束+++++++++++");
        return proceed;
    }

}
