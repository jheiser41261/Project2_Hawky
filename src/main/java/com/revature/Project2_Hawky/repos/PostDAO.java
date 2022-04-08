package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.models.User;

import java.util.List;

public interface PostDAO {
    List<Post> getPostsByAuthor(User author);
    List<Post> getAllPosts();
    Post getPostById(Integer postId);

    Integer createPost(Post post, User author);

    void editPost(Post post);
    void deletePost(Post post);
}
