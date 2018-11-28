package popo.epam.spring.core.loggers;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(String msg) {
        log.info(msg);
    }
}
