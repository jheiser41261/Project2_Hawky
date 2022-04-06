package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public User getUserById(Integer userId) {
        Session session = em.unwrap(Session.class);

        try {
            return session.get(User.class, userId);
        } catch(NoResultException nre){
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = em.unwrap(Session.class);


        try {
            return session.createQuery("from User where username = '" + username + "'", User.class).getSingleResult();
        } catch(NoResultException nre){
            return null;
        }

    }

    @Override
    public Integer createUser(User user) {
        Session session = em.unwrap(Session.class);
        return (Integer) session.save(user);
    }

    @Override
    public void updateInfo(User user) {
        Session session = em.unwrap(Session.class);
        session.update(user);
    }
}
