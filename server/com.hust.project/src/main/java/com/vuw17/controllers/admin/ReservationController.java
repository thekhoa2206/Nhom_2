package com.vuw17.controllers.admin;

import com.vuw17.dto.reservation.*;
import com.vuw17.entities.Reservation;
import com.vuw17.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
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
    @PostMapping
    public ResponseEntity<Void> createReservationById(@Valid @RequestBody ReservationDTORequest reservationDTORequest) throws ParseException {
        reservationService.createReservation(reservationDTORequest);
        return ResponseEntity.ok().build();
    }

    //API sửa đơn đặt phòng
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReservationById(@PathVariable int id,@Valid @RequestBody ReservationDTOUpdateRequest reservationDTOUpdateRequest) throws ParseException {
        reservationService.updateReservation(id, reservationDTOUpdateRequest);
        return ResponseEntity.ok().build();
    }

    //API thay đổi trạng thái đơn đặt phòng
    @PutMapping("/change-status/{id}")
    public ResponseEntity<Void> changeStatusReservation(@PathVariable int id, @RequestParam int status){
        reservationService.changeStatusReservation(id, status);
        return ResponseEntity.ok().build();
    }

    //API lấy list trạng thái đặt phòng
    @PutMapping("/list-status/{id}")
    public ResponseEntity<List<StatusReservation>> listStatusReservation(){
        List<StatusReservation> listStatusReservation = reservationService.listStatusReservation();
        return ResponseEntity.ok(listStatusReservation);
    }

    //API Đặt phòng chi tiết
    @PostMapping("/room")
    public ResponseEntity<Void> chooseRoom(@Valid @RequestBody ReservationRoomDTORequest reservationRoomDTORequest){
        reservationService.chooseRoom(reservationRoomDTORequest);
        return ResponseEntity.ok().build();
    }

    //API đặt phòng
    @PostMapping("/room_reservation")
    public ResponseEntity<Void> reservation(@Valid @RequestBody ReservationRoomDTORequest reservationRoomDTORequest) throws Exception {
        reservationService.reservation(reservationRoomDTORequest);
        return ResponseEntity.ok().build();
    }

}
