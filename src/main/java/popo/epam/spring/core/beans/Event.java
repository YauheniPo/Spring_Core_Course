package popo.epam.spring.core.beans;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
public class Event {

    private int id = UUID.randomUUID().hashCode();
    @Setter @Getter
    private String msg;
    @NonNull private Date date;
    @NonNull private DateFormat df;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
