package popo.epam.spring.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import popo.epam.spring.core.beans.Client;
import popo.epam.spring.core.loggers.EventLogger;

@NoArgsConstructor
@AllArgsConstructor
public class App {

    private Client client;
    private EventLogger eventLogger;

    private void logEvent(String msg) {
        String message = msg.replaceAll(this.client.getId().toString(), this.client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        app.logEvent("Some event for User 1");
    }
}
