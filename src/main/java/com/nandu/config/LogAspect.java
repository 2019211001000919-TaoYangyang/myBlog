package com.nandu.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect      //定义aop切面
@Component
public class LogAspect {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.nandu.controller.*.*(..))") //定义在这个包中及子包的方法，注意有两个点
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //获取请求的url
        String url =request.getRequestURL().toString();
        //获取访问者ip
        String ip=request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        //获取请求的类和方法
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        //获取方法的参数
        Object[] args=joinPoint.getArgs();
        RequstLog requstLog = new RequstLog(url, ip, classMethod, args);
        logger.info("Request:{}",requstLog);
    }


    @After("log()")
    public void doAfter(){
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("result:{}",result);
    }

    private class RequstLog{
        String url ;
        String ip;
        String classMethod;
        Object[] args;

        public RequstLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequstLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}