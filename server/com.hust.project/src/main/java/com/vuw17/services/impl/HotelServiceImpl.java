package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.HotelDao;
import com.vuw17.dto.hotel.HotelDTO;
import com.vuw17.entities.Hotel;
import com.vuw17.services.GenericService;
import com.vuw17.services.HotelService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService, GenericService<HotelDTO> {
    private final HotelDao hotelDao;


    public HotelServiceImpl(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Override
    public String insertOne(HotelDTO hotel) {
        String message = checkInput(hotel);
        if (message == null) {
            hotelDao.insertOne(toEntity(hotel));
            return ConstantVariableCommon.CREATE_SUCCESSFUL;
        }
        return message;
    }

    @Override
    public List<HotelDTO> findAll() {
        return convertList(hotelDao.findAll());
    }

    @Override
    public String updateOne(HotelDTO hotel) {
        int id = hotel.getId();
        if (id <= 0) {
            return ConstantVariableCommon.INVALID_ID;
        } else if (findById(id) == null) {
            return ConstantVariableCommon.NOT_EXIST_ID;
        } else {
            String message = checkInput(hotel);
            if (message == null) {
                hotelDao.updateOne(toEntity(updateData(findById(hotel.getId()), hotel)));
                return ConstantVariableCommon.UPDATE_SUCCESSFUL;
            }
            return message;
        }

    }


    @Override
    public String deleteOne(int id) {
        if (findById(id) != null) {
            HotelDTO hotel = findById(id);
            if (hotel.getStatus() == ConstantVariableCommon.STATUS_HOTEL_3) {
                return ConstantVariableCommon.DELETED_ID;
            } else {
                hotelDao.deleteOne(id);
                return ConstantVariableCommon.DELETE_SUCCESSFUL;
            }
        }
        return ConstantVariableCommon.NOT_EXIST_ID;
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
        return oldData;
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
        } else if (findByName(hotel.getName()) != null) {
            message = ConstantVariableCommon.DUPLICATED_NAME;
        } else if (findByAddress(hotel.getAddress()) != null) {
            message = ConstantVariableCommon.DUPLICATED_ADDRESS;
        } else if (findByPhoneNumber(hotel.getPhoneNumber()) != null) {
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
