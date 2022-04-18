package com.revature.Project2_Hawky.controllers;

import com.revature.Project2_Hawky.models.JsonResponse;
import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("session")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class SessionController {

    private UserService userService;

    @Autowired
    public SessionController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public JsonResponse login(HttpSession httpSession, @RequestBody User user){
        User currentUser = userService.validateCredentials(user.getUsername(), user.getPassword());

        if (currentUser == null)
            return new JsonResponse(false, "Invalid username or password", null);

        httpSession.setAttribute("user", currentUser);
        return new JsonResponse(true, "Logged in", currentUser);
    }

    @GetMapping
    public JsonResponse checkSession(HttpSession httpSession){
        User currentUser = (User) httpSession.getAttribute("user");

        if(currentUser == null)
            return new JsonResponse(false, "No user logged in", null);

        return new JsonResponse(true, "Active user", currentUser);
    }

    @DeleteMapping
    public JsonResponse logout(HttpSession httpSession){
        httpSession.invalidate();
        return new JsonResponse(true, "Logged out", null);
    }
}

