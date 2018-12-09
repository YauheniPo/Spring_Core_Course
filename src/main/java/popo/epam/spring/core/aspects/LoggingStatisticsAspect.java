package popo.epam.spring.core.aspects;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Aspect
@Component
@ToString
public class LoggingStatisticsAspect {

    private Map<Class, Integer> counter = new HashMap<>();

    @Pointcut(value = "execution(* *.logEvent(..))")
    private void allLogEventMethods() {
    }

    @AfterReturning(pointcut = "allLogEventMethods()")
    private void logAfter(JoinPoint joinPoint) {
        Class aClass = joinPoint.getTarget().getClass();
        if (!counter.containsKey(aClass)) {
            counter.put(aClass, 0);
        }
        counter.put(aClass, counter.get(aClass) + 1);
    }
}
