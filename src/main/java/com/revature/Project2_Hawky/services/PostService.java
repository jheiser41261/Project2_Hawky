package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.repos.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class PostService {

    @Autowired
    private PostDAO postDAO;

    public PostService(PostDAO postDAO){
        this.postDAO = postDAO;
    }

    public Post createPost(Post post){
        Integer postId = postDAO.createPost(post);
        return postDAO.getPostById(postId);
    }

    public Post getPostById(Integer postId){

        return postDAO.getPostById(postId);
    }

    public Post getPostByUsername(User user){

        return postDAO.getPostByUsername(user);
    }

    public void deletePost(Integer postId){
        Post post = this.postDAO.getPostById(postId);
        this.postDAO.deletePost(post);
    }

    public Post addLike(Integer postId, Integer like){
        Post post = postDAO.getPostById(postId);

        post.setLikeCount(like);

        postDAO.updatePost(post);
        return post;
    }

}
