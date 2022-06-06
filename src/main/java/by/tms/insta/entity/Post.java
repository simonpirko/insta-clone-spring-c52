package by.tms.insta.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Like> likeList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> commentList;
}