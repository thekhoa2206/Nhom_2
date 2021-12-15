package com.vuw17.services;

import com.vuw17.dto.user.*;
import com.vuw17.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    //Hàm lấy thông tin user bằng token
    UserDTOResponse findInfoUser(String token);

    //Hàm lấy list user DTO
    List<UserDTOResponse> findAllUser();

    //Hàm tạo user
    User createUser(UserDTORequest userDTORequest);

    // hàm lấy user theo id
    UserResponse selectUserById(int id);

    //Hàm sửa thông tin user
    User updateUser(UserDTOUpdateRequest userDTOUpdateRequest, int id);

    //Hàm xóa user
    void deleteUser(int id);

    //Hàm lấy list role
    List<RoleByUserResponseDTO> findAllRole();
}
