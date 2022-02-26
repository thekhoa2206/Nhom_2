package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.GuestDAO;
import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.GuestDao;
import com.vuw17.dao.jpa.TableDiaryDao;
import com.vuw17.dao.jpa.TypeActionDao;
import com.vuw17.dto.UpdateResponse;
import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.Guest;
import com.vuw17.repositories.GuestRepository;
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
    private final GuestRepository guestRepository;

    public GuestServiceImpl(TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, GuestDao guestDao, GuestDAO guestDAO, GuestRepository guestRepository) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.guestDao = guestDao;
        this.guestDAO = guestDAO;
        this.guestRepository = guestRepository;
    }
    @Override
    public int insertOne(GuestDTO guestDTO, UserDTOResponse userDTOResponse) {
        int guestId = 0;
        if(guestDao.findByIdCard(guestDTO.getIdCard()) == null && guestDao.findByPhoneNumber(guestDTO.getPhoneNumber()) == null){
            //int guestId = guestDAO.insertOne(toEntity(guestDTO));

            Guest guest = guestRepository.save(toEntity(guestDTO));
             guestId = guest.getId();
//            if(guestId > 0) {
//                saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE, guestId, ConstantVariableCommon.table_guest, userDTOResponse.getId());
//                return guestId;
//            }
        }
        return guestId;
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

    @Override
    public GuestDTO update(GuestDTO guestDTO, UserDTOResponse userDTOResponse) {
        GuestDTO newData = updateData(findById(guestDTO.getId()),guestDTO);

        boolean check = guestDao.update(toEntity(newData));
        if(check){
            saveDiary(ConstantVariableCommon.TYPE_ACTION_UPDATE, newData.getId(),ConstantVariableCommon.table_guest, userDTOResponse.getId());
            return newData;
        }
        return null;
    }

    @Override
    public UpdateResponse delete(int id, UserDTOResponse userDTOResponse) {
        if(findById(id) != null){
            boolean check = guestDao.delete(id);
            if(check){
                saveDiary(ConstantVariableCommon.TYPE_ACTION_DELETE,id,ConstantVariableCommon.table_guest, userDTOResponse.getId());
                return new UpdateResponse(true);
            }
        }
        return new UpdateResponse(false);
    }

    //Nếu các trường request lên khác null và nếu trường nào khác với bản cũ trong DB thì update
    public GuestDTO updateData(GuestDTO oldData,GuestDTO newData){
        if(!oldData.getFirstName().equals(newData.getFirstName())){
            oldData.setFirstName(newData.getFirstName());
        }
        if(!oldData.getLastName().equals(newData.getLastName())){
            oldData.setLastName(newData.getLastName());
        }
        if(!oldData.getAddress().equals(newData.getAddress())){
            oldData.setAddress(newData.getAddress());
        }
        if(oldData.getBirthday() != newData.getBirthday()){
            oldData.setBirthday(newData.getBirthday());
        }
        if(!oldData.getEmail().equals(newData.getEmail())){
            oldData.setEmail(newData.getEmail());
        }
        if(!oldData.getIdCard().equals(newData.getIdCard())){
            oldData.setIdCard(newData.getIdCard());
        }
        if(!oldData.getNationality().equals(newData.getNationality())){
            oldData.setNationality(newData.getNationality());
        }
        if(!oldData.getPhoneNumber().equals(newData.getPhoneNumber())){
            oldData.setPhoneNumber(newData.getPhoneNumber());
        }
        return oldData;
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
        guest.setStatus(ConstantVariableCommon.STATUS_GUEST_1);
        return guest;
    }
    public GuestDTO toDTO(Guest guest){
        try {
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
        }catch (Exception e){
            return null;
        }
    }
}
