package popo.epam.spring.core.beans;

import lombok.*;

import java.util.Properties;

@RequiredArgsConstructor
public class Client {

    @Getter @Setter
    @NonNull private Integer id;
    @Getter @Setter
    @NonNull private String fullName;
    @Setter
    private String greeting;
    @Getter @Setter
    private Properties properties;
}
