package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.models.User;
import com.revature.Project2_Hawky.repos.PostDAO;
import com.revature.Project2_Hawky.repos.UserDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    private PostService postService;
    private PostDAO postDAO = Mockito.mock(PostDAO.class);
    private UserDAO userDAO = Mockito.mock(UserDAO.class);

    public PostServiceTest(){
        this.postService = new PostService(postDAO);
    }

    @Test
    void createPost() {
        User validUser = new User("user", "pass");
        Mockito.when(userDAO.getUserByUsername(validUser.getUsername())).thenReturn(validUser);

        Post postToAdd = new Post("test", validUser);

        postService.createPost(postToAdd, validUser);

        Mockito.verify(postDAO, Mockito.times(1)).createPost(postToAdd, validUser);
    }

    @Test
    void editPostAsAuthor() {
        User user = new User(1, "user", "pass", "User", "One", "test@test.com", "New York", "NY", "Programmer", "null");
        Post postToEdit = new Post(1, "test", user);
        Mockito.when(postDAO.getPostById(postToEdit.getPostId())).thenReturn(postToEdit);

        User author = postToEdit.getAuthor();

        Post actualResult = postService.editPost(postToEdit, author);

        assertEquals(postToEdit, actualResult);
    }

    @Test
    void editPostAsOtherUser() {
        User user = new User(1, "user", "pass", "User", "One", "test@test.com", "New York", "NY", "Programmer", "null");
        Post postToEdit = new Post(1, "test", user);
        Mockito.when(postDAO.getPostById(postToEdit.getPostId())).thenReturn(postToEdit);

        User notAuthor = new User(2, "user", "pass", "User", "One", "test@test.com", "New York", "NY", "Programmer", "null");

        Post expectedResult = null;

        Post actualResult = postService.editPost(postToEdit, notAuthor);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void likePost(){
        Post postToLike = new Post(1, "test");

        String expectedResult = "Number of Likes on Post #" + 1 + " : " + 1;
        String actualResult = postService.likePost(postToLike);

        assertEquals(expectedResult, actualResult);
    }
}