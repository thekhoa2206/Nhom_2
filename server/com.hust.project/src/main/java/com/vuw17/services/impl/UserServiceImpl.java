package com.vuw17.services.impl;

import com.vuw17.configuration.sercurity.jwt.JwtProvider;
import com.vuw17.dao.jpa.UserDao;
import com.vuw17.dto.user.RoleByUserResponseDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.User;
import com.vuw17.repositories.UserRepository;
import com.vuw17.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public UserServiceImpl(UserDao userDao, JwtProvider jwtProvider, UserRepository userRepository) {
        this.userDao = userDao;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }

    //Hàm tìm thông tin user bằng token
    @Override
    public UserDTOResponse findInfoUser(String token){
//        jwtProvider.validateJwtToken(token);
        System.out.println("token: " +token);
        String[] splits = token.split(" ");
        String username = jwtProvider.getUserNameFromJwtToken(splits[1]);
        User user = userRepository.findUserByUsername(username);
        UserDTOResponse userDTO = new UserDTOResponse();
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        List<RoleByUserResponseDTO> roleName = new ArrayList<>();
        for(int i = 0; i< user.getRoles().size(); i++){
            RoleByUserResponseDTO roleByUserResponseDTO = new RoleByUserResponseDTO();
            roleByUserResponseDTO.setNameRole(user.getRoles().get(i).getName());
            roleName.add(roleByUserResponseDTO);
        }
        userDTO.setRole(roleName);
        return userDTO;
    }
}
