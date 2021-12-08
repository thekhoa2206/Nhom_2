package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.GuestDAO;
import com.vuw17.dao.jdbc.ReservationDAO;
import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.GuestDao;
import com.vuw17.dao.jpa.TableDiaryDao;
import com.vuw17.dao.jpa.TypeActionDao;
import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.Guest;
import com.vuw17.services.BaseService;
import com.vuw17.services.CommonService;
import com.vuw17.services.GuestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestServiceImpl extends CommonService implements GuestService {
    private final GuestDao guestDao;
    private final GuestDAO guestDAO;

    public GuestServiceImpl(TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, GuestDao guestDao, GuestDAO guestDAO) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.guestDao = guestDao;
        this.guestDAO = guestDAO;
    }
    @Override
    public int insertOne(GuestDTO guestDTO, UserDTOResponse userDTOResponse) {
        if(guestDao.findByIdCard(guestDTO.getIdCard()) == null && guestDao.findByPhoneNumber(guestDTO.getPhoneNumber()) == null){
            int guestId = guestDAO.insertOne(toEntity(guestDTO));
            if(guestId > 0) {
                saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE, guestId, ConstantVariableCommon.table_guest, userDTOResponse.getId());
                return guestId;
            }
        }
        return 0;
    }

    @Override
    public List<GuestDTO> findAll() {
        List<GuestDTO> list = new ArrayList<>();
        for(int i = 0;i < guestDao.findAll().size();i++){
            list.add(toDTO(guestDao.findAll().get(i)));
        }
        return list;
    }

    @Override
    public List<GuestDTO> findByKeyword(String keyword) {
        List<GuestDTO> list = new ArrayList<>();
        for(int i = 0;i < guestDao.findByKeyword(keyword).size();i++){
            list.add(toDTO(guestDao.findByKeyword(keyword).get(i)));
        }
        return list;
    }

    @Override
    public GuestDTO findById(int id) {
        return toDTO(guestDao.findById(id));
    }

    public Guest toEntity(GuestDTO guestDTO){
        Guest guest = new Guest();
        guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setAddress(guestDTO.getAddress());
        guest.setBirthday(guestDTO.getBirthday());
        guest.setEmail(guestDTO.getEmail());
        guest.setIdCard(guestDTO.getIdCard());
        guest.setNationality(guestDTO.getNationality());
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
        return guest;
    }
    public GuestDTO toDTO(Guest guest){
        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setId(guest.getId());
        guestDTO.setFirstName(guest.getFirstName());
        guestDTO.setLastName(guest.getLastName());
        guestDTO.setAddress(guest.getAddress());
        guestDTO.setBirthday(guest.getBirthday());
        guestDTO.setEmail(guest.getEmail());
        guestDTO.setIdCard(guest.getIdCard());
        guestDTO.setNationality(guest.getNationality());
        guestDTO.setPhoneNumber(guest.getPhoneNumber());
        return guestDTO;
    }
}
