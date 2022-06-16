package by.tms.insta.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String commentText;
    @ManyToOne
    private Post post;
    private ZonedDateTime dateTime;
    @ManyToOne
    private User user;

    public String getDateTime(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        return dateTime.format(pattern);
    }
}
