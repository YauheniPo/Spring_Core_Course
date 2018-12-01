package popo.epam.spring.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import popo.epam.spring.core.beans.Client;
import popo.epam.spring.core.beans.Event;
import popo.epam.spring.core.loggers.EventLogger;

@NoArgsConstructor
@AllArgsConstructor
public class App {

    private Client client;
    private EventLogger eventLogger;

    private void logEvent(Event event) {
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");
        event.setMsg("Some event for User 1");
        app.logEvent(event);

        ctx.close();
    }
}
