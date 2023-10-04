package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO login(UserDTO userDTO){

        Optional<UserEntity> byEmail = userRepository.findByEmail(userDTO.getEmail());
        if (byEmail.isPresent()){
            UserEntity userEntity = byEmail.get();

            if (userEntity.getPassword().equals(userDTO.getPassword())){
                return  UserDTO.toUserDTO(userEntity);
            }else {
                return null;
            }
        } else{
            return null;
        }
    }

    public void addUser(UserDTO userDTO) {
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
    }
}
