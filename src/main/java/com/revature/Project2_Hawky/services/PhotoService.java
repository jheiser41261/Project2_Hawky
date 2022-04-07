package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.models.Photo;
import com.revature.Project2_Hawky.repos.PhotoDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class PhotoService {

    @Autowired
    private PhotoDAO photoDAO;

    public PhotoService(PhotoDAO photoDAO) {
        this.photoDAO = photoDAO;
    }


}


