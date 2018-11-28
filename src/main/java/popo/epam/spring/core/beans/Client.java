package popo.epam.spring.core.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Client {

    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String fullName;
}
