package com.vuw17.dto.user;

import java.util.List;

public class UserDTOResponse {
    private String username;
    private String name;
    private List<RoleByUserResponseDTO> role;

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
        return role;
    }

    public void setRole(List<RoleByUserResponseDTO> role) {
        this.role = role;
    }
}
