package com.vuw17.services;

import com.vuw17.dto.user.UserDTOResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    //Hàm lấy thông tin user bằng token
    UserDTOResponse findInfoUser(String token);

    //Hàm lấy list user DTO
    List<UserDTOResponse> findAllUser();
}
