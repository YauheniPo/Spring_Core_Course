package popo.epam.spring.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import popo.epam.spring.core.beans.Client;
import popo.epam.spring.core.beans.Event;

@Configuration
@PropertySources({
        @PropertySource("classpath:client.properties"),
})
@ComponentScan("popo.epam.spring.core")
@ImportResource("classpath:spring.xml")
public class AppConfig {

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
}
