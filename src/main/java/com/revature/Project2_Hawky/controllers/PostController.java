package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.models.JsonResponse;
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
    public JsonResponse getPostsByAuthor(@PathVariable String username){
        User author = userService.getUserByUsername(username);
        List<Post> posts = postService.getPostsByAuthor(author);
        return new JsonResponse(true, "Posts by " + username, posts);
    }

    @GetMapping("all")
    public JsonResponse getAllPosts(){
        List<Post> posts = postService.getAllPosts();
        return new JsonResponse(true, "All posts", posts);
    }

    @GetMapping("{postId}")
    public JsonResponse getPostById(@PathVariable Integer postId){
        Post post = postService.getPostById(postId);
        return new JsonResponse(true, "Post #" + postId, post);
    }

    @PostMapping
    public JsonResponse createPost(HttpSession httpSession, @RequestBody Post post){
        User currentUser = (User) httpSession.getAttribute("user");

        if(currentUser == null)
            return new JsonResponse(false, "You must be signed in to create a post", null);

        post.setAuthor(currentUser);
        post.setLikeCount(0);

        Post newPost = postService.createPost(post, currentUser);
        return new JsonResponse(true, "Post created", newPost);
    }

    @PutMapping
    public JsonResponse editPost(HttpSession httpSession, @RequestBody Post post){
        User currentUser = (User) httpSession.getAttribute("user");

        if(currentUser == null)
            return new JsonResponse(false, "You must be signed in to edit a post", null);

        post.setAuthor(currentUser);

        Post editedPost = postService.editPost(post, currentUser);

        return new JsonResponse(true, "Post edited", editedPost);
    }

    @DeleteMapping("delete/{postId}")
    public JsonResponse deletePost(HttpSession httpSession, @PathVariable Integer postId){
        User currentUser = (User) httpSession.getAttribute("user");

        if(currentUser != null) {
            Post post = postService.getPostById(postId);
            postService.deletePost(post);
            return new JsonResponse(true, "Post #" + postId + " deleted", null);
        }

        return new JsonResponse(false, "Error", null);
    }

    @PatchMapping("like/{postId}")
    public String likePost(@PathVariable Integer postId){
        Post post = postService.getPostById(postId);
        return postService.likePost(post);
    }
}
