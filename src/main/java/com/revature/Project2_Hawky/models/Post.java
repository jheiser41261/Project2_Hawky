package com.revature.Project2_Hawky.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post {

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

    @Column(columnDefinition = "int default 0")
    private Integer likeCount;

    @CreationTimestamp
    private Date datePosted;
}
