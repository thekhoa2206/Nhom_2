package com.vuw17.services.impl;

import com.vuw17.configuration.sercurity.jwt.JwtProvider;

import com.vuw17.dao.jpa.UserDao;
import com.vuw17.dto.user.RoleByUserResponseDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.Role;
import com.vuw17.entities.User;
import com.vuw17.repositories.UserRepository;
import com.vuw17.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final UserDao userDao;

    public UserServiceImpl(JwtProvider jwtProvider, UserRepository userRepository, UserDao userDao) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.userDao = userDao;
    }

    //Hàm tìm thông tin user bằng token
    @Override
    public UserDTOResponse findInfoUser(String token){
        try{
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
        }catch (NullPointerException e){
            System.out.println("findInfoUser : "+e.getMessage());
            return null;
        }
    }

    //Hàm lấy list userDTO
    @Override
    public List<UserDTOResponse> findAllUser(){
        List<User> users = userDao.findAllUser();
        List<UserDTOResponse> userDTOResponses = new ArrayList<>();
        for (User user : users){
            UserDTOResponse userDTOResponse = new UserDTOResponse();
            userDTOResponse.setUsername(user.getUsername());
            userDTOResponse.setName(user.getName());
            List<RoleByUserResponseDTO> roleByUserResponseDTOs = new ArrayList<>();
            for (Role role : user.getRoles()){
                RoleByUserResponseDTO roleDTO = new RoleByUserResponseDTO();
                roleDTO.setNameRole(role.getName());
                roleByUserResponseDTOs.add(roleDTO);
            }
            userDTOResponse.setRole(roleByUserResponseDTOs);
            userDTOResponses.add(userDTOResponse);
        }
        return userDTOResponses;
    }
}
