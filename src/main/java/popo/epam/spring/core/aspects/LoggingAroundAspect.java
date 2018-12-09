package popo.epam.spring.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import popo.epam.spring.core.beans.Event;
import popo.epam.spring.core.loggers.FileEventLogger;

@Aspect
@Component
public class LoggingAroundAspect {

    private int MAX_ALLOWER = 3;
    private int count = 0;
    private FileEventLogger fileEventLogger = new FileEventLogger("log/logfile.log");

    @Pointcut(value = "execution(* popo.epam.spring.core.loggers.ConsoleEventLogger*.logEvent(..))")
    private void consoleLogEventMethods() {
    }

    @Around("consoleLogEventMethods() && args(evt)")
    private void aroundLogEvent(ProceedingJoinPoint pjp, Object evt) throws Throwable {
        ++count;
        if (count < MAX_ALLOWER) {
            pjp.proceed(new Object[]{evt});
        } else {
            fileEventLogger.logEvent((Event) evt);
        }
    }
}
