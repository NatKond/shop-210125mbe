package de.telran.shop210125mbe.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class IntervalTimeAspect {

    @Pointcut("@annotation(LogTimeAnnotation)")
    public void callAtAnnotationLogTime() {
    }

    @Around("callAtAnnotationLogTime")
    public Object aroundCallAt(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        log.info("!!!{} duration = {}", pjp.getSignature().getName(), System.currentTimeMillis() - startTime);
        return result;
    }
}
