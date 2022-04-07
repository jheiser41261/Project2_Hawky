package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Photo;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;

@Repository
public class PhotoDAOImpl implements PhotoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(File photo) {
        Session session = em.unwrap(Session.class);
        em.persist(photo);
    }

    @Override
    public void update(File photo) {
        Session session = em.unwrap(Session.class);
        em.merge(photo);
    }

    @Override
    public void delete(File photo) {
        Session session = em.unwrap(Session.class);
        em.remove(photo);
    }

    @Override
    public Photo findById(int photoId) {
        Session session = em.unwrap(Session.class);
        return em.find(Photo.class, photoId);
    }

}