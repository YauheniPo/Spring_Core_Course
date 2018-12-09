package popo.epam.spring.core;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import popo.epam.spring.core.aspects.LoggingStatisticsAspect;
import popo.epam.spring.core.beans.Client;
import popo.epam.spring.core.beans.Event;
import popo.epam.spring.core.config.AppConfig;
import popo.epam.spring.core.loggers.EventLogger;
import popo.epam.spring.core.loggers.EventType;

import javax.annotation.Resource;
import java.util.Map;

@Log4j2
@Component
@Scope(value = "singleton")
public class App implements ApplicationListener {

    @Autowired
    private LoggingStatisticsAspect loggingStatisticsAspect;
    @Resource(name = "cacheFileEventLogger")
    private EventLogger defaultLogger;
    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    public App() {
    }

    private void loggingEvent(EventType type, Event event) {
        EventLogger logger = null;
        if (type != null) {
            logger = loggers.get(type.name());
        }
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ApplicationContext actx = new AnnotationConfigApplicationContext(AppConfig.class);

        App app = actx.getBean(App.class);
        Client client = actx.getBean("getClient", Client.class);
        Event event = actx.getBean("getEvent", Event.class);

        log.info(client.toString());

        event.setMsg("Some event for User 1");
        app.loggingEvent(EventType.ERROR, event);
        event.setMsg("Some event for User 2");
        app.loggingEvent(EventType.INFO, event);
        app.loggingEvent(EventType.INFO, event);
        app.loggingEvent(EventType.INFO, event);
        app.loggingEvent(EventType.INFO, event);
        event.setMsg("Some event for User 3");
        app.loggingEvent(null, event);

        log.info(app.loggingStatisticsAspect.toString());
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        log.info(applicationEvent.toString());
    }
}
