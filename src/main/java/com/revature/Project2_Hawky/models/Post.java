package com.revature.Project2_Hawky.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post {

    public Post(Integer postId, String message){
        this.postId = postId;
        this.message = message;
    }

    public Post(String message, User author){
        this.message = message;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @ManyToOne
    @JoinColumn(name = "author_userId")
    private User author;

    @Column(nullable = false)
    private String message;

    @Column
    private Integer likeCount;

    @CreationTimestamp
    private Date datePosted;
}
