package popo.epam.spring.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import popo.epam.spring.core.beans.Client;
import popo.epam.spring.core.beans.Event;
import popo.epam.spring.core.loggers.*;

import java.text.DateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Configuration
@PropertySources({
        @PropertySource("classpath:client.properties"),
})
@ComponentScan("popo.epam.spring.core")
@ImportResource("classpath:spring.xml")
public class AppConfig {

    @Value("log/logfile.log")
    private String fileName;

    @Value("5")
    private int cacheSize;

    @Autowired
    private Environment env;

    @Bean(name = "user")
    public String getUser() {
        return env.getProperty("USERNAME");
    }

    @Bean
    public Client getClient() {
        return new Client();
    }

    @Bean
    public Event getEvent() {
        return new Event();
    }

    @Bean(name = "timezone")
    public String getTimeZone() {
        return String.valueOf(ZonedDateTime.now(ZoneId.systemDefault()).getZone());
    }

    @Bean()
    public Date getDate() {
        return new Date();
    }

    @Bean
    public DateFormat getDateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean(name = "cacheFileEventLogger")
    public EventLogger getCacheFileEventLogger() {
        return new CacheFileEventLogger(fileName, cacheSize);
    }

    @Bean(name = "fileEventLogger")
    public EventLogger getFileEventLogger() {
        return new FileEventLogger(fileName);
    }

    @Bean(name = "consoleEventLogger")
    public EventLogger getConsoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean(name = "combinedEventLogger")
    public EventLogger getCombinedEventLogger() {
        return new CombinedEventLogger(Arrays.asList(getConsoleEventLogger(), getFileEventLogger()));
    }

    @Bean(name = "loggerMap")
    public HashMap getLogMap() {
        return new HashMap() {{
            put(EventType.INFO, getConsoleEventLogger());
            put(EventType.ERROR, getCombinedEventLogger());
        }};
    }
}
