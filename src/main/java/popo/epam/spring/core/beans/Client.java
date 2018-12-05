package popo.epam.spring.core.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@ToString
@Getter @Setter
@Component
public class Client {

    @Value("${client.id}")
    private Integer id;
    @Value("${client.name}")
    private String fullName;
    @Value("${client.greeting}")
    private String greeting;
    @Resource(name = "user")
    private String user;
}
