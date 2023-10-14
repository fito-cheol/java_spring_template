package com.rest.api.service;

import com.rest.api.dto.UserDTO;
import com.rest.api.entity.UserEntity;
import com.rest.api.repository.UserRepository;
import com.rest.api.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public String login(UserDTO userDTO){

        Optional<UserEntity> byEmail = userRepository.findByEmail(userDTO.getEmail());
        if (byEmail.isPresent()){
            UserEntity userEntity = byEmail.get();

            if (userEntity.getPassword().equals(userDTO.getPassword())){
                UserDTO userDTO1 = UserDTO.toUserDTO(userEntity);

                return jwtUtil.create(userDTO1);
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
    public UserDTO findUserByEmail(String email){
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            UserEntity userEntity = byEmail.get();
            return UserDTO.toUserDTO(userEntity);
        }else{
            return null;
        }
    }
}
