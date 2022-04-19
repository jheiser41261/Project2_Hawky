package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.models.JsonResponse;
import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.services.AWSS3Service;
import com.revature.Project2_Hawky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class UserController {

    private UserService userService;
    private AWSS3Service awss3Service;

    @Autowired
    public UserController(UserService userService, AWSS3Service awss3Service){
        this.userService = userService;
        this.awss3Service = awss3Service;
    }

    @GetMapping("{userId}")
    public JsonResponse getUserById(@PathVariable Integer userId){
        User user = userService.getUserById(userId);
        return new JsonResponse(true, "Information for User #" + user.getUserId(), user);
    }

    @GetMapping("username/{username}")
    public JsonResponse getUserByUsername(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        return new JsonResponse(true, "Information for User " + user.getUsername(), user);
    }

    @PostMapping
    public JsonResponse createUser(@RequestBody User user){
        User newUser = userService.createUser(user);
        return new JsonResponse(true, "User created", newUser);
    }

    @PutMapping
    public JsonResponse updateInfo(HttpSession httpSession, @RequestBody User user){
        User updatedUser = userService.updateInfo(user);
        httpSession.setAttribute("user", updatedUser);

        return new JsonResponse(true, "User " + user.getUsername() + " updated", updatedUser);
    }

    @PostMapping("upload")
    public JsonResponse uploadPhoto(HttpSession httpSession, @RequestParam("file") MultipartFile file){
        String publicURL = awss3Service.uploadFile(file);

        User currentUser = (User) httpSession.getAttribute("user");
        currentUser.setProfilePhoto(publicURL);
        userService.updateInfo(currentUser);

        return new JsonResponse(true, "Picture uploaded successfully", publicURL);
    }

}
