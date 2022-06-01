package by.tms.insta.dao;

import by.tms.insta.entity.Like;

import java.util.List;

public interface LikeStorage {
    List<Like> findLikesByPost(Long postId);

    boolean saveLike(Like like);


    List<Like> findLikesByUser(Long userId);

    List<Like> findAllLikes();


}
