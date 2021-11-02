package com.vuw17.controllers;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.services.UserService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseController {
    private final UserService userService;

    public BaseController(UserService userService) {
        this.userService = userService;
    }

    public UserDTOResponse getUserDTOResponse(HttpServletRequest request){
        return userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION));
    }
}
