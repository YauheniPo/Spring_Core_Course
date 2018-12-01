package popo.epam.spring.core.loggers;

import popo.epam.spring.core.beans.Event;

public interface EventLogger {

    void logEvent(Event event);
}
