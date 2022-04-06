package com.revature.Project2_Hawky.repos;

import org.aspectj.util.FileUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PhotoDAOImpl implements PhotoDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public FileUtil getPhoto(int photoId) {
        return null;
    }

    @Override
    public FileUtil delPhoto(int photoId) {
        return null;
    }
}
