package com.revature.Project2_Hawky.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photoId;

    @ManyToOne
    private User postId;

    @Column(nullable = false, length = 333)
    private File photo;
}