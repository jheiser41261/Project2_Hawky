package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private UserController userController;
    private PostService postService;

    public PhotoController(PostService postService) {
        this.postService = postService;
    }







}


