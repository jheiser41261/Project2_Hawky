package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("session")
public class SessionController {

    private UserService userService;

    @Autowired
    public SessionController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> login(HttpSession httpSession, @RequestBody User user){
        User currentUser = userService.validateCredentials(user.getUsername(), user.getPassword());

        if (currentUser == null)
            return ResponseEntity.ok("Invalid username or password");

        httpSession.setAttribute("user", currentUser);
        return ResponseEntity.ok("Signed in as user: " + user.getUsername());
    }

    @GetMapping
    public ResponseEntity<User> checkSession(HttpSession httpSession){
        User currentUser = (User) httpSession.getAttribute("user");

        if(currentUser == null)
            return ResponseEntity.ok(null);

        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping
    public ResponseEntity<String> logout(HttpSession httpSession){
        httpSession.invalidate();
        return ResponseEntity.ok("Logged out");
    }
}

