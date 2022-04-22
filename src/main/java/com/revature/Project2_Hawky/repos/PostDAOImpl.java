package com.revature.Project2_Hawky.repos;

import com.revature.Project2_Hawky.models.Post;
import com.revature.Project2_Hawky.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostDAOImpl implements PostDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Post> getPostsByAuthor(User author) {
        Session session = em.unwrap(Session.class);
        return session.createQuery("from Post where author = '" + author.getUserId() + "'", Post.class).getResultList();
    }

    @Override
    public List<Post> getAllPosts() {
        Session session = em.unwrap(Session.class);
        return session.createQuery("from Post", Post.class).getResultList();
    }

    @Override
    public Post getPostById(Integer postId){
        Session session = em.unwrap(Session.class);

        try {
            return session.get(Post.class, postId);
        } catch(NoResultException nre){
            return null;
        }
    }

    @Override
    public Integer createPost(Post post, User author) {
        Session session = em.unwrap(Session.class);
        post.setAuthor(author);
        return (Integer) session.save(post);
    }

    @Override
    public void editPost(Post post) {
        Session session = em.unwrap(Session.class);
        session.update(post);
    }

    @Override
    public void deletePost(Post post) {
        Session session = em.unwrap(Session.class);
        session.delete(post);
    }

/*    @Override
    public void updateLikeCount(Integer postId, Integer postId_fk) {
        Session session = em.unwrap(Session.class);

        String hql = "update Post set like_Count = " + "(select count(*) from likes where post_Id_fk = :post_Id_fk) " + "where post_Id = :postId";

        Query query = session.createQuery(hql);
        query.setParameter("post_Id_fk", postId_fk);
        query.setParameter("postId", postId);

        query.executeUpdate();
    }*/
}
