package com.vuw17.services;

import com.vuw17.dto.BillResponseDTO;
import com.vuw17.dto.bill.BillResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {
    List<BillResponse> findAll();
}
