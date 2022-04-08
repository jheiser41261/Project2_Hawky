package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.models.Photo;
import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.services.AWSS3Service;
import com.revature.Project2_Hawky.services.PhotoService;
import com.revature.Project2_Hawky.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("photo")
public class PhotoController {

    private PhotoService photoService;
    private PostService postService;
    private AWSS3Service awss3Service;

    @Autowired
    public PhotoController(PhotoService photoService, PostService postService, AWSS3Service awss3Service){
        this.photoService = photoService;
        this.postService = postService;
        this.awss3Service = awss3Service;
    }

    @PostMapping("upload/{postId}")
    public ResponseEntity<Map<String, String>> uploadPhoto(@PathVariable Integer postId, @RequestParam("photo")MultipartFile photo){
        String publicURL = awss3Service.uploadFile(photo);

        Post post = postService.getPostById(postId);
        photoService.uploadPhoto(new Photo(post, publicURL));

        Map<String, String> response = new HashMap<>();
        response.put("publicURL", publicURL);
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
    }

    @GetMapping("{photoId}")
    public Photo getPhotoById(@PathVariable Integer photoId){
        return photoService.getPhotoById(photoId);
    }

    @GetMapping("post/{postId}")
    public List<Photo> getPhotosByPost(@PathVariable Integer postId){
        Post post = postService.getPostById(postId);
        return photoService.getPhotosByPost(post);
    }

    @DeleteMapping("delete/{photoId}")
    public String deletePhoto(@PathVariable Integer photoId){
        Photo photo = photoService.getPhotoById(photoId);
        photoService.deletePhoto(photo);
        return "Photo #" + photoId + " deleted";
    }
}


