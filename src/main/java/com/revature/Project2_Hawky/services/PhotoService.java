package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.models.Photo;
import com.revature.Project2_Hawky.repos.PhotoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;

@Service
@Transactional
public class PhotoService {

    @Autowired
    private PhotoDAO photoDAO;

    public PhotoService(PhotoDAO photoDAO) {
        this.photoDAO = photoDAO;
    }

    public Photo findById(int photoId) {
        return photoDAO.findById(photoId);
    }

    public void save(File photo) {
        photoDAO.save(photo);
    }

    public void delete(File photo) {
        photoDAO.delete(photo);
    }
    public void update(File photo) {
        photoDAO.update(photo);
    }

}





