package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

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
    public User updateInfo(@RequestBody User user){
        return userService.updateInfo(user);
    }

    //todo Add Login, Logout, and Check-Session


}
