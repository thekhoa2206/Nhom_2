package com.vuw17.dto.user;

import java.util.List;

public class UserDTOResponse {
    private int id;
    private String username;
    private String name;
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
}
