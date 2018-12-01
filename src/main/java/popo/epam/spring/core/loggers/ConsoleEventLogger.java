package popo.epam.spring.core.loggers;

import lombok.extern.log4j.Log4j2;
import popo.epam.spring.core.beans.Event;

@Log4j2
public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event) {
        log.info(event.toString());
    }
}
