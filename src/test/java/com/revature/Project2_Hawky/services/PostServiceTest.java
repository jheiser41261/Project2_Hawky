package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.repos.PostDAO;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    private PostService postService;
    private PostDAO postDAO = Mockito.mock(PostDAO.class);

    public PostServiceTest(){
        this.postService = new PostService(postDAO);
    }


}