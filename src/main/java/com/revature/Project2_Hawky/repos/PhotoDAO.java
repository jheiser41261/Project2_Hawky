package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Photo;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface PhotoDAO {

    void save(File photo);

    void delete(File photo);

    Photo findById(int photoId);

    void update(File photo);

}