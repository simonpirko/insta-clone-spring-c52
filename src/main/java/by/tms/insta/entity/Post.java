package by.tms.insta.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @NotNull
    private User user;
    @Lob
    @NotNull
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Like> likeList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> commentList;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", image=" + Arrays.toString(image) +
                ", description='" + description + '\'' +
                '}';
    }
}
