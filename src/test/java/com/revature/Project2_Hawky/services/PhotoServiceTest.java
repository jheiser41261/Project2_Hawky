package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.models.Photo;
import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.repos.PhotoDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PhotoServiceTest {

    private PhotoService photoService;
    private PhotoDAO photoDAO = Mockito.mock(PhotoDAO.class);

    public PhotoServiceTest(){
        this.photoService = new PhotoService(photoDAO);
    }

    @Test
    void uploadPhoto() {
        Post testPost = new Post(1, "test");
        Photo photoToUpload = new Photo(testPost, "test.jpg");

        photoService.uploadPhoto(photoToUpload);

        Mockito.verify(photoDAO, Mockito.times(1)).uploadPhoto(photoToUpload);
    }
}