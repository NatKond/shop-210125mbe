package de.telran.shop210125mbe.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static de.telran.shop210125mbe.textFormatting.CYAN;
import static de.telran.shop210125mbe.textFormatting.RESET;

@Aspect
@Component
public class IntervalTimeAspect {

    private static final Logger log = LoggerFactory.getLogger(IntervalTimeAspect.class);

    @Around("@annotation(LogTimeAnnotation)")
    public Object aroundCallAtAnnotationLogTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        log.info("{}!!! {} duration = {}{}", CYAN, pjp.getSignature().getName(), System.currentTimeMillis() - startTime, RESET);
        return result;
    }
}
