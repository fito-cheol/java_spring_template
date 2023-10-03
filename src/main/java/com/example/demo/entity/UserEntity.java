package com.example.demo.entity;

import com.example.demo.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="users")
public class UserEntity {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    public UserEntity(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static UserEntity toUserEntity(UserDTO userDTO){
        return  new UserEntity(null, userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
    }
}
