package com.revature.Project2_Hawky.services;

import com.revature.Project2_Hawky.models.Like;
import com.revature.Project2_Hawky.repos.LikeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LikeService {

    private LikeDAO likeDAO;

    @Autowired
    public LikeService(LikeDAO likeDAO) { this.likeDAO = likeDAO; }

    public Like likePost(Like like){
        Integer likeId = likeDAO.likePost(like);
        return likeDAO.getLikeById(likeId);
    }

    public Like getLike(Integer likeId){
        return likeDAO.getLikeById(likeId);
    }

    public void unlikePost(Integer likeId){
        Like like = likeDAO.getLikeById(likeId);
        likeDAO.unlikePost(like);
    }

}
