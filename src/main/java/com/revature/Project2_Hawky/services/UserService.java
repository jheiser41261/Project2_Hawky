package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User createUser(User user){
        Integer userId = userDAO.createUser(user);
        return userDAO.getUserById(userId);
    }

    public User getUserById(Integer userId){
        return userDAO.getUserById(userId);
    }

    public User getUserByUsername(String username){
        return userDAO.getUserByUsername(username);
    }

    public User updateInfo(User user){
        userDAO.updateInfo(user);
        return userDAO.getUserById(user.getUserId());
    }

    public User validateCredentials(String username, String password){
        User user = userDAO.getUserByUsername(username);

        if(user == null)
            return null;

        if(!password.equals(user.getPassword()))
            return null;

        return user;
    }
}
