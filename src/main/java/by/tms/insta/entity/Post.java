package by.tms.insta.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity @NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @Lob
    @Column(name = "image", columnDefinition="BLOB")
    private byte[] image;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Like> likeList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> commentList;
}
