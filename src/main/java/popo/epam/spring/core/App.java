package popo.epam.spring.core;

import lombok.AllArgsConstructor;
import popo.epam.spring.core.beans.Client;
import popo.epam.spring.core.loggers.ConsoleEventLogger;
import popo.epam.spring.core.loggers.EventLogger;

@AllArgsConstructor
public class App {

    private Client client;
    private EventLogger eventLogger;

    private void logEvent(String msg) {
        String message = msg.replaceAll(this.client.getId().toString(), this.client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        App app = new App(new Client(1, "Yauheni Papovich"), new ConsoleEventLogger());

        app.logEvent("Some event for User 1");
    }
}
