package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Photo;
import org.aspectj.util.FileUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;

public class PhotoDAOImpl implements PhotoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Photo photo) {
        em.persist(photo);
    }

    @Override
    public void update(Photo photo) {
        em.merge(photo);
    }

    @Override
    public void delete(Photo photo) {
        em.remove(photo);
    }

    @Override
    public Photo findById(int id) {
        return em.find(Photo.class, id);
    }

}