package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.repos.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostService {

    private PostDAO postDAO;

    @Autowired
    public PostService(PostDAO postDAO){
        this.postDAO = postDAO;
    }

    public List<Post> getPostsByAuthor(User author){
        return postDAO.getPostsByAuthor(author);
    }

    public List<Post> getAllPosts(){
        return postDAO.getAllPosts();
    }

    public Post getPostById(Integer postId){
        return postDAO.getPostById(postId);
    }

    public Post createPost(Post post, User author){
        Integer postId = postDAO.createPost(post, author);
        return postDAO.getPostById(postId);
    }

    public Post editPost(Post post, User author){

        if(post.getAuthor() != author)
            return null;

        postDAO.editPost(post);
        return postDAO.getPostById(post.getPostId());
    }

    public void deletePost(Post post){
        postDAO.deletePost(post);
    }

/*    public String likePost(Post post, User user){

        user.setUserId(user.getUserId());
        if(post.getLikeCount() == null)
            post.setLikeCount(0);

        if(post.userHasLiked == false){
            post.setLikeCount(post.getLikeCount() + 1);
            post.userHasLiked = true;
        }

        postDAO.editPost(post);

        return "Number of Likes on Post #" + post.getPostId() + " : " + post.getLikeCount();
    }

    public String unlikePost(Post post, User user){

        user.setUserId(user.getUserId());

        if(post.userHasLiked == true){
            post.setLikeCount(post.getLikeCount() - 1);
            post.userHasLiked = false;
        }

        postDAO.editPost(post);

        return "Number of Likes on Post #" + post.getPostId() + " : " + post.getLikeCount();
    }*/
}
