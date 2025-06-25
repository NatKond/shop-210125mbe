package de.telran.shop210125mbe.aspect;

import de.telran.shop210125mbe.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ReplacementAspect {
    // замена функциональности метода
    @Pointcut("execution(public * de.telran.shop210125mbe.controller.ProductController.getProduct(..))")
    public void callAtProductControllerPublic(ProceedingJoinPoint pjp) {

    }

    @Around("callAtProductControllerPublic")
    public Object aroundCallAt(ProceedingJoinPoint pjp) throws Throwable {
        log.info("+++ {}", pjp.getSignature().getName());
        Object retVal = null;
        retVal = pjp.proceed();
        UserDto userDtoTest2 = new UserDto(
                99L,
                "Дуня Смирнова",
                "dunya@i.com",
                "+491607654321",
                "Password2",
                "CLIENT",
                null,
                null
        );
        // если параметр = 1, то заменим на заглушку
        if(Arrays.stream(pjp.getArgs()).anyMatch(a -> a.toString().equals("1"))) {
            retVal = userDtoTest2;
        }
        return retVal;
    }
}
