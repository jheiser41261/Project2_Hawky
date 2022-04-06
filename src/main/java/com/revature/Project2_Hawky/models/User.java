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
@Table(name = "users")
public class User {

    public User (String username){
        this.username = username;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true, nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(unique = true, nullable = false, length = 40)
    private String email;

    @Column(length = 30)
    private String city;

    @Column(length = 2)
    private String state;

    @Column(length = 50)
    private String occupation;

    @Column
    private File profilePicture;
}
