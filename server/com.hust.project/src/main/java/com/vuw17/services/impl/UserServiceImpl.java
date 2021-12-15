package com.vuw17.services.impl;

import com.vuw17.common.Common;
import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.configuration.sercurity.jwt.JwtProvider;

import com.vuw17.dao.jpa.UserDao;
import com.vuw17.dto.user.*;
import com.vuw17.entities.Role;
import com.vuw17.entities.User;
import com.vuw17.repositories.RoleRepository;
import com.vuw17.repositories.UserRepository;
import com.vuw17.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final UserDao userDao;
    private final RoleRepository roleRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class.toString());

    public UserServiceImpl(JwtProvider jwtProvider, UserRepository userRepository, UserDao userDao, RoleRepository roleRepository) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.userDao = userDao;
        this.roleRepository = roleRepository;
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
            UserDTOResponse userDTO = transferUserDTOResponse(user);
            return userDTO;
        }catch (NullPointerException e){
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
            userDTOResponse.setId(user.getId());
            userDTOResponse.setUsername(user.getUsername());
            userDTOResponse.setName(user.getName());
            userDTOResponse.setAddress(user.getAddress());
            userDTOResponse.setEmail(user.getEmail());
            userDTOResponse.setPhone(user.getPhone());
            userDTOResponse.setSalaryDay(user.getSalaryDay());
            userDTOResponse.setSex(Common.getStringSex(user.getSex()));
            userDTOResponse.setStatus(ConstantVariableCommon.changeIntToStringUserStatus(user.getStatus()));
            userDTOResponse.setIdCard(user.getIdCard());
            List<RoleUserDTO> roleDTOs = new ArrayList<>();
            for (Role role : user.getRoles()){
                RoleUserDTO roleDTO = new RoleUserDTO();
                roleDTO.setId(role.getId());
                roleDTOs.add(roleDTO);
            }
            userDTOResponse.setRoles(roleDTOs);
            userDTOResponses.add(userDTOResponse);
        }
        return userDTOResponses;
    }

    //Hàm tạo user
    @Override
    public User createUser(UserDTORequest userDTORequest){
        User user = new User();
        user.setName(userDTORequest.getName());
        user.setUsername(userDTORequest.getUsername());
        user.setPassword(Common.GeneratePassword(userDTORequest.getPassword()));
        user.setAddress(userDTORequest.getAddress());
        user.setEmail(userDTORequest.getEmail());
        user.setIdCard(userDTORequest.getIdCard());
        user.setPhone(userDTORequest.getPhone());
        user.setSalaryDay(userDTORequest.getSalaryDay());
        user.setStatus(ConstantVariableCommon.STATUS_USER_1);
        user.setSex(userDTORequest.isSex());
        List<Role> roles = new ArrayList<>();
        for (RoleUserDTO roleDTO: userDTORequest.getRoles()) {
            Role role = userDao.findRoleById(roleDTO.getId());
            roles.add(role);
        }
        user.setRoles(roles);
        saveUser(user);
        User userResponse = userRepository.findUserByUsername(user.getUsername());
        return userResponse;
    }

    //Hàm lưu người dùng
    @Transactional(rollbackOn = Exception.class)
    public void saveUser(User user){
        try{
            userRepository.save(user);
        }catch (Exception e){
            LOGGER.error("ERROR || Lỗi không lưu được người dùng service", e.getMessage());
        }
    }

    private UserDTOResponse transferUserDTOResponse(User user){
        UserDTOResponse userDTO = new UserDTOResponse();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setIdCard(user.getIdCard());
        userDTO.setPhone(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        userDTO.setSalaryDay(user.getSalaryDay());
        userDTO.setSex(Common.getStringSex(user.getSex()));
        userDTO.setStatus(ConstantVariableCommon.changeIntToStringUserStatus(user.getStatus()));
        List<RoleUserDTO> roles = new ArrayList<>();
        for(int i = 0; i< user.getRoles().size(); i++){
            RoleUserDTO roleByUserResponseDTO = new RoleUserDTO();
            roleByUserResponseDTO.setId(user.getRoles().get(i).getId());
            roles.add(roleByUserResponseDTO);
        }
        userDTO.setRoles(roles);
        return userDTO;
    }


    // hàm lấy user theo id
    @Override
    public UserDTOResponse selectUserById(int id){
        User user = userRepository.findUserById(id);
        UserDTOResponse userDTOResponse = transferUserDTOResponse(user);
        return userDTOResponse;
    }

    //Hàm sửa thông tin user
    @Override
    @Transactional(rollbackOn = Exception.class)
    public User updateUser(UserDTOUpdateRequest userDTOUpdateRequest, int id){
        User user = userRepository.findUserById(id);
        user.setName(userDTOUpdateRequest.getName());
        user.setUsername(userDTOUpdateRequest.getUsername());
        user.setAddress(userDTOUpdateRequest.getAddress());
        user.setEmail(userDTOUpdateRequest.getEmail());
        user.setIdCard(userDTOUpdateRequest.getIdCard());
        user.setPhone(userDTOUpdateRequest.getPhone());
        user.setSalaryDay(userDTOUpdateRequest.getSalaryDay());
        user.setStatus(ConstantVariableCommon.STATUS_USER_1);
        user.setSex(userDTOUpdateRequest.isSex());
        List<Role> roles = new ArrayList<>();
        for (RoleUserDTO roleDTO: userDTOUpdateRequest.getRoles()) {
            Role role = userDao.findRoleById(roleDTO.getId());
            roles.add(role);
        }
        user.setRoles(roles);
        saveUser(user);
        User userResponse = userRepository.findUserByUsername(user.getUsername());
        return userResponse;
    }

    //Hàm xóa user
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteUser(int id){
        User user = userRepository.findUserById(id);
        userRepository.delete(user);
    }

    //Hàm lấy list role
    @Override
    public List<RoleByUserResponseDTO> findAllRole(){
        List<Role> roles = roleRepository.findAll();
        List<RoleByUserResponseDTO> roleDTOs = new ArrayList<>();
        for (Role role: roles) {
            RoleByUserResponseDTO roleDTO = new RoleByUserResponseDTO();
            roleDTO.setId(role.getId());
            roleDTO.setName(role.getName());
            roleDTO.setDescription(role.getDescription());
            roleDTOs.add(roleDTO);
        }
        return roleDTOs;
    }
}
