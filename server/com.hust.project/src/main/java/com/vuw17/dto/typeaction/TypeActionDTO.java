package com.vuw17.dto.typeaction;

import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeActionDTO extends BaseDTO {
    private String name;

    private String note;

    private int status;
}
