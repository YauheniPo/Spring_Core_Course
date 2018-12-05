package popo.epam.spring.core;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import popo.epam.spring.core.beans.Client;
import popo.epam.spring.core.beans.Event;
import popo.epam.spring.core.loggers.EventLogger;
import popo.epam.spring.core.loggers.EventType;

import javax.annotation.Resource;
import java.util.Map;

@Log4j2
@Component
@Scope(value = "singleton")
public class App implements ApplicationListener {

    @Autowired(required = false)
    private Client client;
    @Resource(name = "cacheFileEventLogger")
    private EventLogger defaultLogger;
    @Resource(name = "loggerMap")
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

        App app = ctx.getBean("app", App.class);
        Event event = ctx.getBean("event", Event.class);

        log.info(app.client.toString());

        event.setMsg("Some event for User 1");
        app.logEvent(EventType.ERROR, event);
        event.setMsg("Some event for User 2");
        app.logEvent(EventType.INFO, event);
        event.setMsg("Some event for User 3");
        app.logEvent(null, event);

        ctx.close();
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        log.info(applicationEvent.toString());
    }
}
