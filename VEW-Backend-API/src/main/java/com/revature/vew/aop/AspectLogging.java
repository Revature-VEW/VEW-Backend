package com.revature.vew.aop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
@EnableAspectJAutoProxy
public class AspectLogging {
    private final Logger log = LogManager.getLogger(this.getClass());


    /**
     * Pointcut that matches all repos
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)\" +\n" +
            "        \" || within(@org.springframework.stereotype.Service *)\" +\n" +
            "        \" || within(@org.springframework.web.bind.annotation.RestController *)\"))")
    public void springBeanPointcut(){
    }

    /**
     * Pointcut that matches all spring beans in the controllers, services, and repos.
     */
    @Pointcut("within(com.revature.vew.controllers..*)" +
            " || within(com.revature.vew.services..*)" +
            " || within(com.revature.vew.repositories..*)")
    public void applicationPackagePointcut(){
    }

    /**
     * Advice that logs methods throwing exceptions
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e){
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() !=null ? e.getCause() : "NULL");
    }

    /**
     * Advice that logs when a method is entered and exited
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        if (log.isDebugEnabled()){
            log.debug("Enter: {}.{}() with arguments = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
    @Around("execution(* com.revature.vew..*(..)))")
    public Object allMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        log.info("Execution time of " + className + "." + methodName + " "
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }
}
