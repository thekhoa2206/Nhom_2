package com.vuw17.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
public class UserDTOUpdateRequest {
    @NotBlank(message = "Tên đăng nhập không được để trống!")
    @NotNull(message = "Tên đăng nhập không được để trống!")
    private String username;
    @NotBlank(message = "Tên người dùng không được để trống!")
    @NotNull(message = "Tên người dùng không được để trống!")
    private String name;
    private String address;
    private String email;
    @NotBlank(message = "Số điện thoại không được để trống!")
    @NotNull(message = "Số điện thoại không được để trống!")
    @Size(min = 10, max = 10, message = "Số điện thoại không đúng định dạng")
    private String phone;
    private boolean sex;
    private Double salaryDay;
    @NotBlank(message = "Id card không được để trống!")
    @NotNull(message = "Id card không được để trống!")
    private String idCard;
    private List<Integer> roleIds;
}
