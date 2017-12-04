package com.ms.server.ds;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.system.ds.DbContextHolder;

@Aspect
@Order(0)
@Component
public class DataSourceAspect {
    
    
    @Before("execution(* com.message.admin.*.dao.*.*(..)) || execution(* com.message.admin.*.service.*.*(..))")
    public void frame() {
    	DbContextHolder.setDsName("dataSource1");
    }

    /*@Before("execution(* com.frame.test.dao.*.*(..)) || execution(* com.frame.test.service.*.*(..))")
    public void test() {
    	DbContextHolder.setDbType("dataSource2");
    }*/
    
}