package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.services.PostService;
import com.revature.Project2_Hawky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
public class PostController {

    private PostService postService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("{postId}")
    public Post getPostById(@PathVariable Integer postId){
        return postService.getPostById(postId);
    }

    @GetMapping("username/{userId}")
    public Post getPostByUsername(@PathVariable Integer userId){
        User user = userService.getUserById(userId);
        return postService.getPostByUsername(user);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }


    @DeleteMapping("{postId}")
    public String deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);

        return "post with id " + postId + " removed if post exists";
    }

}
