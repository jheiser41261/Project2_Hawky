package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Photo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;

public class PhotoDAOImpl implements PhotoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(File photo) {
        em.persist(photo);
    }

    @Override
    public void update(File photo) {
        em.merge(photo);
    }

    @Override
    public void delete(File photo) {
        em.remove(photo);
    }

    @Override
    public Photo findById(int photoId) {
        return em.find(Photo.class, photoId);
    }

}