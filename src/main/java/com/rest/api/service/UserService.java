package com.rest.api.service;

import com.rest.api.configuration.EncrypterConfig;
import com.rest.api.dto.UserDTO;
import com.rest.api.entity.UserEntity;
import com.rest.api.repository.UserRepository;
import com.rest.api.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final EncrypterConfig encrypterConfig;

    private final JwtUtil jwtUtil;

    public String login(UserDTO userDTO) throws RuntimeException{

        Optional<UserEntity> byEmail = userRepository.findByEmail(userDTO.getEmail());
        if (byEmail.isEmpty()){
            throw new RuntimeException("해당하는 이메일이 존재하지 않습니다");
        }
        UserEntity userEntity = byEmail.get();

        if (userEntity.checkPassword(userDTO.getPassword(), encrypterConfig.encodePwd())){
            UserDTO userDTO1 = UserDTO.toUserDTO(userEntity);

            return jwtUtil.create(userDTO1);
        }else {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }
    }

    public UserDTO addUser(UserDTO userDTO) throws RuntimeException {
        // TODO 이미 존재하는 회원 (이메일 중복)
        UserDTO userFoundByEmail = findUserByEmail(userDTO.getEmail());
        if (userFoundByEmail != null){
            throw new RuntimeException("이메일이 이미 존재합니다");
        }
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        UserEntity hashedUserEntity = userEntity.HasPassword(encrypterConfig.encodePwd());
        return UserDTO.toUserDTO(userRepository.save(hashedUserEntity));
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
