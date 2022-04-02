package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.User;

public interface UserDAO {
    User getUserById(Integer userId);
    User getUserByUsername(String username);

    Integer createUser(User user);

    void updateInfo(User user);
}
