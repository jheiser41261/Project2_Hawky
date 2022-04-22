package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.repos.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;
    private UserDAO userDAO = Mockito.mock(UserDAO.class);

    public UserServiceTest(){
        this.userService = new UserService(userDAO);
    }

    @Test
    void createUser(){
        User newUser = new User(
                1,
                "user",
                "pass",
                "User",
                "One",
                "test@test.com",
                "New York",
                "NY",
                "Programmer",
                "test.jpg"
        );
        Mockito.when(userDAO.getUserById(newUser.getUserId())).thenReturn(newUser);

        userService.createUser(newUser);

        User expectedResult = newUser;
        User actualResult = userService.getUserById(newUser.getUserId());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void editUserInfo() {
        User userToEdit = new User(
                1,
                "user",
                "pass",
                "User",
                "One",
                "test@test.com",
                "New York",
                "NY",
                "Programmer",
                "test.jpg"
        );
        Mockito.when(userDAO.getUserById(userToEdit.getUserId())).thenReturn(userToEdit);

        User expectedResult = userToEdit;
        User actualResult = userService.updateInfo(userToEdit);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateCredentialsBadUsername() {
        String expectedUsername = "incorrect";
        String expectedPassword = "correct";
        User expectedOutput = null;
        Mockito.when(userDAO.getUserByUsername(expectedUsername)).thenReturn(expectedOutput);

        User actualOutput = userService.validateCredentials(expectedUsername, expectedPassword);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void validateCredentialsBadPassword() {
        String expectedUsername = "correct";
        String expectedPassword = "incorrect";
        User expectedOutput = null;
        User userFromDb = new User("correct", "correct");
        Mockito.when(userDAO.getUserByUsername(expectedUsername)).thenReturn(userFromDb);

        User actualOutput = userService.validateCredentials(expectedUsername, expectedPassword);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void validateCredentialsGoodLogin() {
        String expectedUsername = "correct";
        String expectedPassword = "correct";
        User expectedOutput = new User(expectedUsername, expectedPassword);
        Mockito.when(userDAO.getUserByUsername(expectedUsername)).thenReturn(expectedOutput);

        User actualOutput = userService.validateCredentials(expectedUsername, expectedPassword);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}