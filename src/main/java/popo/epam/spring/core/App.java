package popo.epam.spring.core;

import popo.epam.spring.core.beans.Client;
import popo.epam.spring.core.loggers.ConsoleEventLogger;
import popo.epam.spring.core.loggers.EventLogger;

public class App {

    private Client client;
    private EventLogger eventLogger;

    private void logEvent(String msg) {
        String message = msg.replaceAll(this.client.getId().toString(), this.client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        App app = new App();

        app.client = new Client(1, "Yauheni Papovich");
        app.eventLogger = new ConsoleEventLogger();

        app.logEvent("Some event for User 1");
    }
}
