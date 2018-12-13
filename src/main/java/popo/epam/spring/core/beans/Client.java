package popo.epam.spring.core.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ToString
@Getter @Setter
@Component
@PropertySource(ignoreResourceNotFound = true, value = "classpath:client.properties")
public class Client {

    @Value("${client.id}")
    private Integer id;
    @Value("${client.name}")
    private String fullName;
    @Value("${client.greeting}")
    private String greeting;
    @Value("#{systemEnvironment['USERNAME']}")
    private String user;
}
