package popo.epam.spring.core.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class LoggingAspect {

    @Pointcut(value = "execution(* *.logEvent(..))")
    private void allLogEventMethods() {
    }

    @Before(value = "allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("BEFORE : " +
                joinPoint.getTarget().getClass().getSimpleName() +
                " " + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "allLogEventMethods()",
            returning = "retVal")
    public void logAfter(Object retVal) {
        log.info("Returned value: " + retVal);
    }

    @AfterThrowing(
            pointcut = "allLogEventMethods()",
            throwing = "ex")
    public void logAfterThrow(Throwable ex) {
        log.warn("Thrown: " + ex);
    }
}
