package com.vuw17.dto.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ServiceUsedDTORequest {
    @Size(min = 1,message = "Ma dich vu phai > 0")
    private int serviceId;
    @Size(min = 1,message = "So luong phai > 0")
    private int quantity;
    private boolean paid;
}
