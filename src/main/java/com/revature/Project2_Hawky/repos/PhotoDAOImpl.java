package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Photo;
import com.revature.Project2_Hawky.models.Post;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PhotoDAOImpl implements PhotoDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Integer uploadPhoto(Photo photo) {
        Session session = em.unwrap(Session.class);
        return (Integer) session.save(photo);
    }

    @Override
    public Photo getPhotoById(Integer photoId) {
        Session session = em.unwrap(Session.class);
        return session.get(Photo.class, photoId);
    }

    @Override
    public List<Photo> getPhotosByPost(Post post) {
        Session session = em.unwrap(Session.class);
        return session.createQuery("from Photo where post = " + post.getPostId(), Photo.class).getResultList();
    }

    @Override
    public void deletePhoto(Photo photo) {
        Session session = em.unwrap(Session.class);
        session.delete(photo);
    }
}