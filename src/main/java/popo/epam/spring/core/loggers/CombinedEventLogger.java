package popo.epam.spring.core.loggers;

import lombok.AllArgsConstructor;
import popo.epam.spring.core.beans.Event;

import javax.annotation.Resource;
import java.util.Collection;

@AllArgsConstructor
public class CombinedEventLogger implements EventLogger {

//    @Resource(name = "combinedEventLogger")
    private Collection<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        loggers.forEach(logger -> logger.logEvent(event));
    }
}
