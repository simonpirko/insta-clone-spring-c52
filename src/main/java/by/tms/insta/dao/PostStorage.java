package by.tms.insta.dao;

import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;

import java.util.List;

public interface PostStorage {

    boolean savePost(Post post);

    List<Post> findAllPosts();

    Post findPostsByUser (User user);
}
