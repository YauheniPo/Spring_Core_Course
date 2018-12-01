package popo.epam.spring.core;

import lombok.AllArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import popo.epam.spring.core.beans.Client;
import popo.epam.spring.core.beans.Event;
import popo.epam.spring.core.loggers.EventLogger;
import popo.epam.spring.core.loggers.EventType;

import java.util.Map;

@AllArgsConstructor
public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    private void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");
        event.setMsg("Some event for User 1");
        app.logEvent(EventType.ERROR, event);
        event.setMsg("Some event for User 2");
        app.logEvent(EventType.INFO, event);
        event.setMsg("Some event for User 3");
        app.logEvent(null, event);

        ctx.close();
    }
}
