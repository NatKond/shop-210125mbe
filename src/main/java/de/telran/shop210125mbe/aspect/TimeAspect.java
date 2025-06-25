package de.telran.shop210125mbe.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import static de.telran.shop210125mbe.textFormatting.PURPLE;
import static de.telran.shop210125mbe.textFormatting.RESET;

@Aspect
@Component
public class TimeAspect {

    private static final Logger log = LoggerFactory.getLogger(TimeAspect.class);

    @Pointcut("execution(public * de.telran.shop210125mbe.controller.UserController.*(..))")
    public void callAtUserControllerPublic() {
    } // join-point

    @Before("callAtUserControllerPublic()")
    public void beforeCallAtMethod(JoinPoint jp) {
        log.info("{}--- start --- {} time -> {}{}", PURPLE, jp.toShortString(), LocalTime.now(), RESET);
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info("{}before {}, args = [{}]{}", PURPLE, jp.toShortString(), args, RESET);
    }

    @After("callAtUserControllerPublic()")
    private void afterCallAtMethod(JoinPoint jp) {
        log.info("{}--- end --- {} time -> {}{}", PURPLE, jp.toShortString(), LocalTime.now(), RESET);
    }
}
