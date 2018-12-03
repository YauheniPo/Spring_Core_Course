package popo.epam.spring.core.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

@Component
@Scope(value = "prototype")
public class Event {

    private int id = UUID.randomUUID().hashCode();
    @Setter @Getter
    private String msg;
    @Autowired(required = false)
    @Qualifier(value = "date")
    private Date date;
    @Autowired(required = false)
    @Qualifier(value = "dateFormat")
    private DateFormat df;
    @Resource(name = "timezone")
    private String timezone;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
