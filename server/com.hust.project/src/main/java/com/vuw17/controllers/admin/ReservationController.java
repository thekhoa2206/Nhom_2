package com.vuw17.controllers.admin;

import com.vuw17.dto.reservation.ReservationDTOResponse;
import com.vuw17.dto.reservation.ReservationDetailDTOResponse;
import com.vuw17.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    //API lấy danh sách đặt phòng
    @GetMapping("/list")
    public ResponseEntity<List<ReservationDTOResponse>> listReservation(@RequestParam String keyword){
        List<ReservationDTOResponse> reservationDTOResponses = reservationService.findAllReservationByKeyword(keyword);
        return ResponseEntity.ok(reservationDTOResponses);
    }
    //API xem chi tiết đơn đặt phòng
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDetailDTOResponse> getReservationById(@PathVariable("id") int id){
        ReservationDetailDTOResponse reservationDetailDTOResponse = reservationService.findReservationDTOById(id);
        return ResponseEntity.ok(reservationDetailDTOResponse);
    }
    //API tạo đơn đặt phòng
    //API sửa đơn đặt phòng
    //API thay đổi trạng thái đơn đặt phòng

}
