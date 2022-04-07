package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.models.Photo;
import com.revature.Project2_Hawky.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping
    public void save(@RequestBody File photo){
        photoService.save(photo);
    }

    @GetMapping("{photoId}")
    public Photo findById(@PathVariable Integer photoId){
        return photoService.findById(photoId);
    }

    @PatchMapping("")
    public void update(@PathVariable File photo){
        photoService.update(photo);
    }

    @DeleteMapping("{photo}")
    public String delete(@PathVariable File photo){
        this.photoService.delete(photo);

        return "photo deleted";
    }

}


