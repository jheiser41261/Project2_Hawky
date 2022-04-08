package com.revature.Project2_Hawky.controllers;

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
public class UserController {

    private UserService userService;
    private AWSS3Service awss3Service;

    @Autowired
    public UserController(UserService userService, AWSS3Service awss3Service){
        this.userService = userService;
        this.awss3Service = awss3Service;
    }

    @GetMapping("{userId}")
    public User getUserById(@PathVariable Integer userId){
        return userService.getUserById(userId);
    }

    @GetMapping("username/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping
    public User updateInfo(HttpSession httpSession, @RequestBody User user){
        User updatedUser = userService.updateInfo(user);
        httpSession.setAttribute("user", updatedUser);

        return updatedUser;
    }

    @PostMapping("upload")
    public ResponseEntity<Map<String, String>> uploadPhoto(HttpSession httpSession, @RequestParam("file") MultipartFile file){
        String publicURL = awss3Service.uploadFile(file);

        User currentUser = (User) httpSession.getAttribute("user");
        currentUser.setProfilePhoto(publicURL);
        userService.updateInfo(currentUser);

        Map<String, String> response = new HashMap<>();
        response.put("publicURL", publicURL);
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
    }

}
