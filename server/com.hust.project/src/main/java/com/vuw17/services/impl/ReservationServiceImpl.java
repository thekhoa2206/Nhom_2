package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.GuestDAO;
import com.vuw17.dao.jdbc.ReservationDAO;
import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.GuestDao;
import com.vuw17.dao.jpa.TableDiaryDao;
import com.vuw17.dao.jpa.TypeActionDao;
import com.vuw17.dto.reservation.ReservationDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.Reservation;
import com.vuw17.services.BaseService;
import com.vuw17.services.CommonService;
import com.vuw17.services.ReservationService;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl extends CommonService implements ReservationService {
    private final ReservationDAO reservationDAO;
    private final TableDiaryDAO tableDiaryDAO;
    private final TypeActionDAO typeActionDAO;
    private final TypeActionDao typeActionDao;
    private final TableDiaryDao tableDiaryDao;
    private final BaseService baseService;
    private final GuestDao guestDao;
    private final GuestDAO guestDAO;

    public ReservationServiceImpl(ReservationDAO reservationDAO, TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, GuestDao guestDao, GuestDAO guestDAO) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.reservationDAO = reservationDAO;
        this.tableDiaryDAO = tableDiaryDAO;
        this.typeActionDAO = typeActionDAO;
        this.typeActionDao = typeActionDao;
        this.tableDiaryDao = tableDiaryDao;
        this.baseService = baseService;
        this.guestDao = guestDao;
        this.guestDAO = guestDAO;
    }

    @Override
    public int insertOne(ReservationDTO reservationDTO, UserDTOResponse userDTOResponse) {
        int guestId = reservationDTO.getGuestId();
        if(guestDao.findById(guestId) == null){

        }



        int reservationId = reservationDAO.insertOne(toEntity(reservationDTO));
        if(reservationId > 0){
            saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE,reservationId,ConstantVariableCommon.table_reservation,userDTOResponse.getId());

        }
        return 0;
    }

    public Reservation toEntity(ReservationDTO reservationDTO) {
        return new Reservation(reservationDTO.getNote(), reservationDTO.getDateFrom(), reservationDTO.getDateTo(), reservationDTO.getStatus(), reservationDTO.getNumberRoom(), reservationDTO.getGuestId());
    }
}
