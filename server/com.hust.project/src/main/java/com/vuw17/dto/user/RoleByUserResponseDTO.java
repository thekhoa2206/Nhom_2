package com.vuw17.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleByUserResponseDTO {
    private int id;
    private String name;
    private String description;
}
