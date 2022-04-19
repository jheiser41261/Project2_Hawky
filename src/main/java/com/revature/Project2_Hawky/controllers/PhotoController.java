package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.models.JsonResponse;
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
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
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
    public JsonResponse uploadPhoto(@PathVariable Integer postId, @RequestParam("photo")MultipartFile photo){
        String publicURL = awss3Service.uploadFile(photo);

        Post post = postService.getPostById(postId);
        photoService.uploadPhoto(new Photo(post, publicURL));

        return new JsonResponse(true, "Picture uploaded successfully", publicURL);
    }

    @GetMapping("{photoId}")
    public JsonResponse getPhotoById(@PathVariable Integer photoId){
        Photo photo = photoService.getPhotoById(photoId);
        return new JsonResponse(true, "URL for Photo #" + photoId, photo);
    }

    @GetMapping("post/{postId}")
    public JsonResponse getPhotosByPost(@PathVariable Integer postId){
        Post post = postService.getPostById(postId);
        List<Photo> photos = photoService.getPhotosByPost(post);
        return new JsonResponse(true, "Photos for Post #" + postId, photos);
    }

    @DeleteMapping("delete/{photoId}")
    public JsonResponse deletePhoto(@PathVariable Integer photoId){
        Photo photo = photoService.getPhotoById(photoId);
        photoService.deletePhoto(photo);
        return new JsonResponse(true, "Photo #" + photoId + " deleted", null);
    }
}


