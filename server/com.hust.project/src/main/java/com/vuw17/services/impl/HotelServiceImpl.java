package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.HotelDAO;
import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.HotelDao;
import com.vuw17.dao.jpa.TableDiaryDao;
import com.vuw17.dao.jpa.TypeActionDao;
import com.vuw17.dto.base.DiaryDTO;
import com.vuw17.dto.hotel.HotelDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.Hotel;
import com.vuw17.services.CommonService;
import com.vuw17.services.GenericService;
import com.vuw17.services.HotelService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl extends CommonService implements HotelService, GenericService<HotelDTO> {
    private final HotelDao hotelDao;
    private final HotelDAO hotelDAO;
    private final BaseServiceImpl baseService;
    private final TableDiaryDAO tableDiaryDAO;
    private final TypeActionDAO typeActionDAO;
    private final TypeActionDao typeActionDao;
    private final TableDiaryDao tableDiaryDao;


    public HotelServiceImpl(HotelDao hotelDao, HotelDAO hotelDAO, BaseServiceImpl baseService, TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao);
        this.hotelDao = hotelDao;
        this.hotelDAO = hotelDAO;
        this.baseService = baseService;
        this.tableDiaryDAO = tableDiaryDAO;
        this.typeActionDAO = typeActionDAO;
        this.typeActionDao = typeActionDao;
        this.tableDiaryDao = tableDiaryDao;
    }

    @Override
    public int insertOne(HotelDTO hotel, UserDTOResponse userDTOResponse) {
        String message = checkInput(hotel);
        if (message == null) {
            int id = hotelDAO.insertOne(toEntity(hotel));
            if (id > 0) {
                DiaryDTO diaryDTO = checkDiary(ConstantVariableCommon.TYPE_ACTION_CREATE, id, ConstantVariableCommon.table_hotel);
                diaryDTO.setUserId(userDTOResponse.getId());
                baseService.saveDiary(diaryDTO);
                return id;
            }
        }
        return 0;
    }

    @Override
    public List<HotelDTO> findAll() {
        return convertList(hotelDao.findAll());
    }

    @Override
    public boolean updateOne(HotelDTO hotel, UserDTOResponse userDTOResponse) {
        int id = hotel.getId();
        String message = checkInput(hotel);
        HotelDTO hotelDTO = findById(id);
        if (id > 0 && hotelDTO != null && message == null && !checkDataNotChanges(hotelDTO, hotel)) {
            HotelDTO hotelUpdate = updateData(hotelDTO, hotel);
            boolean checkUpdated = hotelDao.updateOne(toEntity(hotelUpdate));
            if (checkUpdated) {
                DiaryDTO diaryDTO = checkDiary(ConstantVariableCommon.TYPE_ACTION_UPDATE, id, ConstantVariableCommon.table_hotel);
                diaryDTO.setUserId(userDTOResponse.getId());
                baseService.saveDiary(diaryDTO);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean deleteOne(int id, UserDTOResponse userDTOResponse) {
        HotelDTO hotel = findById(id);
        if (hotel != null && hotel.getStatus() != ConstantVariableCommon.STATUS_HOTEL_3 && hotelDao.deleteOne(id)) {
            DiaryDTO diaryDTO = checkDiary(ConstantVariableCommon.TYPE_ACTION_DELETE, id, ConstantVariableCommon.table_hotel);
            diaryDTO.setUserId(userDTOResponse.getId());
            baseService.saveDiary(diaryDTO);
            return true;
        }
        return false;
    }

    @Override
    public HotelDTO findById(int id) {
        Hotel hotel = hotelDao.findById(id);
        if (hotel != null) {
            return toDTO(hotel);
        }
        return null;
    }

    @Override
    public HotelDTO findByAddress(String address) {
        Hotel hotel = hotelDao.findByAddress(address);
        if (hotel != null) {
            return toDTO(hotel);
        }
        return null;
    }

    @Override
    public HotelDTO findByName(String name) {
        Hotel hotel = hotelDao.findByName(name);
        if (hotel != null) {
            return toDTO(hotel);
        }
        return null;
    }

    @Override
    public HotelDTO findByPhoneNumber(String phoneNumber) {
        Hotel hotel = hotelDao.findByPhoneNumber(phoneNumber);
        if (hotel != null) {
            return toDTO(hotel);
        }
        return null;
    }

    @Override
    public List<HotelDTO> findByStatus(int status) {
        return convertList(hotelDao.findByStatus(status));
    }

    public HotelDTO updateData(HotelDTO oldData, HotelDTO newData) {

        if (StringUtils.hasText(newData.getName())) {
            oldData.setName(newData.getName());
        }
        if (StringUtils.hasText(newData.getPhoneNumber())) {
            oldData.setPhoneNumber(newData.getPhoneNumber());
        }
        if (StringUtils.hasText(newData.getNote())) {
            oldData.setNote(newData.getNote());
        }
        if (StringUtils.hasText(newData.getAddress())) {
            oldData.setAddress(newData.getAddress());
        }
        if (newData.getStatus() > 0 && newData.getStatus() <= ConstantVariableCommon.STATUS_HOTEL_3 && newData.getStatus() != oldData.getStatus()) {
            oldData.setStatus(newData.getStatus());
        }
        return oldData;
    }

    //Chi can 1 data bi thay doi la cho update,neu khong thay doi cai gi thi khong cho
    public boolean checkDataNotChanges(HotelDTO hotelDTOBefore, HotelDTO hotelDTOAfter) {
        boolean checkName = hotelDTOBefore.getName().equalsIgnoreCase(hotelDTOAfter.getName());
        boolean checkAddress = hotelDTOBefore.getAddress().equalsIgnoreCase(hotelDTOAfter.getAddress());
        boolean checkPhoneNumber = hotelDTOBefore.getPhoneNumber().equals(hotelDTOAfter.getPhoneNumber());
        boolean checkNote = hotelDTOBefore.getNote().equalsIgnoreCase(hotelDTOAfter.getNote());
        return checkName && checkAddress && checkPhoneNumber && checkNote;
    }

    public Hotel toEntity(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setPhoneNumber(hotelDTO.getPhoneNumber());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setNote(hotelDTO.getNote());
        hotel.setStatus(hotelDTO.getStatus());

        return hotel;
    }
    //Vi luc insert id = null nen se ko set truong Id

    public HotelDTO toDTO(Hotel hotel) {
        HotelDTO hotelDTO = commonTransferData(hotel);
        hotelDTO.setId(hotel.getId());
        return hotelDTO;
    }

//    public HotelDTO toDTOWhenInsert(Hotel hotel) {
//        return commonTransferData(hotel);
//    }

    public HotelDTO commonTransferData(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setPhoneNumber(hotel.getPhoneNumber());
        hotelDTO.setNote(hotel.getNote());
        hotelDTO.setStatus(hotel.getStatus());
        return hotelDTO;
    }

    public String checkInput(HotelDTO hotel) {
        String message = null;
        if (hotel.getPhoneNumber().length() != 10 || !hotel.getPhoneNumber().startsWith("0")) {
            message = ConstantVariableCommon.INVALID_PHONE;
        } else if (findByName(hotel.getName()) != null && findByName(hotel.getName()).getId() != hotel.getId()) {
            message = ConstantVariableCommon.DUPLICATED_NAME;
        } else if (findByAddress(hotel.getAddress()) != null && findByAddress(hotel.getAddress()).getId() != hotel.getId()) {
            message = ConstantVariableCommon.DUPLICATED_ADDRESS;
        } else if (findByPhoneNumber(hotel.getPhoneNumber()) != null && findByPhoneNumber(hotel.getPhoneNumber()).getId() != hotel.getId()) {
            message = ConstantVariableCommon.DUPLICATED_PHONE;
        }
        return message;
    }
    public List<HotelDTO> convertList(List<Hotel> hotels){
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelDTOs.add(toDTO(hotel));
        }
        return hotelDTOs;
    }


}
