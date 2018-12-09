package popo.epam.spring.core;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
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

    @Resource(name = "cacheFileEventLogger")
    private EventLogger defaultLogger;
    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    private void loggingEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
//        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationContext actx = new AnnotationConfigApplicationContext(AppConfig.class);

        App app = actx.getBean(App.class);
        Client client = actx.getBean("getClient", Client.class);
        Event event = actx.getBean("getEvent", Event.class);

        log.info(client.toString());

        event.setMsg("Some event for User 1");
        app.loggingEvent(EventType.ERROR, event);
        event.setMsg("Some event for User 2");
        app.loggingEvent(EventType.INFO, event);
        event.setMsg("Some event for User 3");
        app.loggingEvent(null, event);

//        ctx.close();
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        log.info(applicationEvent.toString());
    }
}
