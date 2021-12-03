package com.vuw17.services;

import com.vuw17.dto.service.ServiceDTORequest;
import com.vuw17.dto.service.ServiceDTOResponse;
import com.vuw17.dto.user.UserDTOResponse;

import java.util.List;

public interface ServiceService {
    int insertOne(ServiceDTORequest serviceDTORequest, UserDTOResponse userDTOResponse);
    List<ServiceDTOResponse> findAll();
}
