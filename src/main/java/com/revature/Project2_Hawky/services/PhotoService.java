package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.models.Photo;
import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.repos.PhotoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PhotoService {

    private PhotoDAO photoDAO;

    @Autowired
    public PhotoService(PhotoDAO photoDAO){
        this.photoDAO = photoDAO;
    }

    public Photo uploadPhoto(Photo photo){
        Integer photoId = photoDAO.uploadPhoto(photo);
        return photoDAO.getPhotoById(photoId);
    }

    public Photo getPhotoById(Integer photoId){
        return photoDAO.getPhotoById(photoId);
    }

    public List<Photo> getPhotosByPost(Post post){
        return photoDAO.getPhotosByPost(post);
    }

    public void deletePhoto(Photo photo){
        photoDAO.deletePhoto(photo);
    }
}





