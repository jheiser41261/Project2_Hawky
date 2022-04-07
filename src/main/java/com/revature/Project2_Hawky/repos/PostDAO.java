package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.models.User;

public interface PostDAO {
    Post getPostById(Integer postId);
    Post getPostByUsername(User user);
    Integer createPost(Post post);
    void deletePost(Post post);
    void updatePost(Post post);
}
