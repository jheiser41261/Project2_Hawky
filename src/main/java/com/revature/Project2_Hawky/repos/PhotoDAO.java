package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Photo;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface PhotoDAO {

    void save(Photo photo);

    void delete(Photo photo);

    Photo findById(int photoId);

    void update(Photo photo);

}