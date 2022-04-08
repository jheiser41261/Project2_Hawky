package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Photo;
import com.revature.Project2_Hawky.models.Post;

import java.util.List;

public interface PhotoDAO {
    Integer uploadPhoto(Photo photo);
    Photo getPhotoById(Integer photoId);

    List<Photo> getPhotosByPost(Post post);

    void deletePhoto(Photo photo);
}