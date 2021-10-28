package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.HotelDao;
import com.vuw17.dto.hotel.HotelDTORequest;
import com.vuw17.entities.Hotel;
import com.vuw17.services.GenericService;
import com.vuw17.services.HotelService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService, GenericService<HotelDTORequest> {
    private final HotelDao hotelDao;


    public HotelServiceImpl(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Override
    public String insertOne(HotelDTORequest hotel) {
        String message = checkInput(hotel);
        if (message == null) {
            hotelDao.insertOne(toEntity(hotel));
            return ConstantVariableCommon.CREATE_SUCCESSFUL;
        }
        return message;
    }

    @Override
    public List<HotelDTORequest> findAll() {
        List<HotelDTORequest> hotelDTORequests = new ArrayList<>();
        List<Hotel> hotels = hotelDao.findAll();
        for (Hotel hotel : hotels) {
            hotelDTORequests.add(toDTO(hotel));
        }
        return hotelDTORequests;
    }

    @Override
    public String updateOne(HotelDTORequest hotel) {
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
            HotelDTORequest hotel = findById(id);
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
    public HotelDTORequest findById(int id) {
        Hotel hotel = hotelDao.findById(id);
        if (hotel != null) {
            return toDTO(hotel);
        }
        return null;
    }

    @Override
    public HotelDTORequest findByAddress(String address) {
        Hotel hotel = hotelDao.findByAddress(address);
        if (hotel != null) {
            return toDTO(hotel);
        }
        return null;
    }

    @Override
    public HotelDTORequest findByName(String name) {
        Hotel hotel = hotelDao.findByName(name);
        if (hotel != null) {
            return toDTO(hotel);
        }
        return null;
    }

    @Override
    public HotelDTORequest findByPhoneNumber(String phoneNumber) {
        Hotel hotel = hotelDao.findByPhoneNumber(phoneNumber);
        if (hotel != null) {
            return toDTO(hotel);
        }
        return null;
    }

    public HotelDTORequest updateData(HotelDTORequest oldData, HotelDTORequest newData) {

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

    public Hotel toEntity(HotelDTORequest hotelDTORequest) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTORequest.getId());
        hotel.setName(hotelDTORequest.getName());
        hotel.setPhoneNumber(hotelDTORequest.getPhoneNumber());
        hotel.setAddress(hotelDTORequest.getAddress());
        hotel.setNote(hotelDTORequest.getNote());
        hotel.setStatus(hotelDTORequest.getStatus());

        return hotel;
    }
    //Vi luc insert id = null nen se ko set truong Id

    public HotelDTORequest toDTO(Hotel hotel) {
        HotelDTORequest hotelDTORequest = commonTransferData(hotel);
        hotelDTORequest.setId(hotel.getId());
        return hotelDTORequest;
    }

    public HotelDTORequest toDTOWhenInsert(Hotel hotel) {
        return commonTransferData(hotel);
    }

    public HotelDTORequest commonTransferData(Hotel hotel) {
        HotelDTORequest hotelDTORequest = new HotelDTORequest();
        hotelDTORequest.setName(hotel.getName());
        hotelDTORequest.setAddress(hotel.getAddress());
        hotelDTORequest.setPhoneNumber(hotel.getPhoneNumber());
        hotelDTORequest.setNote(hotel.getNote());
        hotelDTORequest.setStatus(hotel.getStatus());
        return hotelDTORequest;
    }

    public String checkInput(HotelDTORequest hotel) {
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

}
