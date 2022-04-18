package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.services.PostService;
import com.revature.Project2_Hawky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("post")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class PostController {

    private PostService postService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("author/{username}")
    public List<Post> getPostsByAuthor(@PathVariable String username){
        User author = userService.getUserByUsername(username);
        return postService.getPostsByAuthor(author);
    }

    @GetMapping("all")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("{postId}")
    public Post getPostById(@PathVariable Integer postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public Post createPost(HttpSession httpSession, @RequestBody Post post){
        User currentUser = (User) httpSession.getAttribute("user");

        if(currentUser == null)
            return null;

        post.setAuthor(currentUser);
        post.setLikeCount(0);

        return postService.createPost(post, currentUser);
    }

    @PutMapping
    public Post editPost(HttpSession httpSession, @RequestBody Post post){
        User currentUser = (User) httpSession.getAttribute("user");

        if(currentUser == null)
            return null;

        post.setAuthor(currentUser);

        return postService.editPost(post, currentUser);
    }

    @DeleteMapping("delete/{postId}")
    public String deletePost(HttpSession httpSession, @PathVariable Integer postId){
        User currentUser = (User) httpSession.getAttribute("user");

        if(currentUser != null) {
            Post post = postService.getPostById(postId);
            postService.deletePost(post);
            return "Post #" + postId + " deleted";
        }

        return "Error";
    }

    @PatchMapping("like/{postId}")
    public String likePost(@PathVariable Integer postId){
        Post post = postService.getPostById(postId);
        return postService.likePost(post);
    }
}
