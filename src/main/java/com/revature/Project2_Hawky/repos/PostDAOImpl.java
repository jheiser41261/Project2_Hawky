package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.models.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PostDAOImpl implements PostDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Post getPostById(Integer postId) {
        Session session = em.unwrap(Session.class);

        return session.get(Post.class, postId);
    }

    @Override //double check
    public Post getPostByUsername(User user) {
        Session session = em.unwrap(Session.class);
        return session.createQuery("from Post where author = '" + user.getUserId() + "'", Post.class)
                .getSingleResult();
    }

    @Override
    public Integer createPost(Post post) {
        Session session = em.unwrap(Session.class);
        return (Integer) session.save(post);
    }

    @Override
    public void deletePost(Post post) {
        Session session = em.unwrap(Session.class);
        session.update(post);
    }

    @Override
    public void updateLike(Post post) {
        Session session = em.unwrap(Session.class);
        session.update(post);
    }
}
