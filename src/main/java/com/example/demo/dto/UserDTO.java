package com.example.demo.dto;

import com.example.demo.entity.UserEntity;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;

    public static UserDTO toUserDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setUsername(userEntity.getUsername());
        return userDTO;
    }
}
