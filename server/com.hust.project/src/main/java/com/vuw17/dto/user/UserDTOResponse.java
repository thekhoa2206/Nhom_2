package com.vuw17.dto.user;

import java.math.BigDecimal;
import java.util.List;

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
    private List<RoleByUserResponseDTO> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoleByUserResponseDTO> getRole() {
        return roles;
    }

    public void setRole(List<RoleByUserResponseDTO> role) {
        this.roles = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getSalaryDay() {
        return salaryDay;
    }

    public void setSalaryDay(Double salaryDay) {
        this.salaryDay = salaryDay;
    }
}
