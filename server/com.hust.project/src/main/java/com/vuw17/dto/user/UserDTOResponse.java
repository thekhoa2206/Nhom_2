package com.vuw17.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
public class UserDTOResponse {
    private int id;
    private String username;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String sex;
    private Double salaryDay;
    private String idCard;
    private String status;
    private List<RoleUserDTO> roles;

}
