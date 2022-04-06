package com.revature.Project2_Hawky.repos;

import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoDAO {

    FileUtil getPhoto(int photoId);

    FileUtil delPhoto(int photoId);
}
