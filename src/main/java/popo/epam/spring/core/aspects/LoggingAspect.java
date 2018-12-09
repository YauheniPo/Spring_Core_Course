package popo.epam.spring.core.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component //or <bean id="logaspect" class="...LoggingAspect"/>
public class LoggingAspect {

    @Pointcut(value = "execution(* *.logEvent(..))")
    private void allLogEventMethods() {
    }

    @Before(value = "allLogEventMethods()")
    private void logBefore(JoinPoint joinPoint) {
        log.info("BEFORE : " +
                joinPoint.getTarget().getClass().getSimpleName() +
                " " + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "allLogEventMethods()",
            returning = "retVal")
    private void logAfter(Object retVal) {
        log.info("Returned value: " + retVal);
    }

    @AfterThrowing(
            pointcut = "allLogEventMethods()",
            throwing = "ex")
    private void logAfterThrow(Throwable ex) {
        log.warn("Thrown: " + ex);
    }
}
