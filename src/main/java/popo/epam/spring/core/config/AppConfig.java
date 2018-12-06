package popo.epam.spring.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import popo.epam.spring.core.beans.Client;
import popo.epam.spring.core.beans.Event;

@Configuration
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public Client getClient() {
        return new Client();
    }

    @Bean(name = "user")
    public String getUser() {
        return env.getProperty("USERNAME");
    }

    @Autowired
    public Event event;
}
