package com.rest.api.entity;

import com.rest.api.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
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

    public static UserEntity toUserEntity(UserDTO userDTO){
        return  new UserEntity(null, userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
    }
    public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, password);
    }
    public UserEntity HasPassword(PasswordEncoder passwordEncoder){
        password = passwordEncoder.encode(password);
        return this;
    }
}
