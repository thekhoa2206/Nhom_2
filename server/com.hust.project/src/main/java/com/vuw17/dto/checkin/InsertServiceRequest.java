package com.vuw17.dto.checkin;


import com.vuw17.dto.service.ServiceUsedDTORequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class InsertServiceRequest {
    private int roomId;
    private List<ServiceUsedDTORequest> services;
}
