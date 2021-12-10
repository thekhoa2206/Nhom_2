package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.*;
import com.vuw17.dao.jpa.*;
import com.vuw17.dto.base.DiaryDTO;
import com.vuw17.dto.typeprice.TypePriceDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.TypePrice;
import com.vuw17.services.BaseService;
import com.vuw17.services.CommonService;
import com.vuw17.services.TypePriceService;
import org.springframework.stereotype.Service;

@Service
public class TypePriceServiceImpl extends CommonService implements TypePriceService {
    private final TableDiaryDAO tableDiaryDAO;
    private final TypeActionDAO typeActionDAO;
    private final TypeActionDao typeActionDao;
    private final TableDiaryDao tableDiaryDao;
    private final BaseService baseService;
    private final TypePriceDao typePriceDao;
    private final TypePriceDAO typePriceDAO;

    public TypePriceServiceImpl(TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, TypePriceDao typePriceDao, TypePriceDAO typePriceDAO) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.tableDiaryDAO = tableDiaryDAO;
        this.typeActionDAO = typeActionDAO;
        this.typeActionDao = typeActionDao;
        this.tableDiaryDao = tableDiaryDao;
        this.baseService = baseService;
        this.typePriceDao = typePriceDao;
        this.typePriceDAO = typePriceDAO;
    }

    @Override
    public int insertOne(TypePriceDTO typePriceDTO, UserDTOResponse userDTOResponse) {
        String message = checkInput(typePriceDTO);
        if (message == null) {
            int typePriceId = typePriceDAO.insertOne(toEntity(typePriceDTO));
            if (typePriceId > 0) {
                DiaryDTO diaryDTO = checkDiary(ConstantVariableCommon.TYPE_ACTION_CREATE, typePriceId, ConstantVariableCommon.table_type_price);
                diaryDTO.setUserId(userDTOResponse.getId());
                baseService.saveDiary(diaryDTO);
                return typePriceId;
            }
        }
        return 0;
    }
    public String checkInput(TypePriceDTO typePriceDTO) {
        String message = null;
        if (findByName(typePriceDTO.getName()) != null) {
            message = ConstantVariableCommon.DUPLICATED_NAME;
        }
        return message;
    }
    @Override
    public TypePriceDTO findByName(String name) {
        TypePrice typePrice = typePriceDao.findByName(name);
        if (typePrice != null) {
            return toDTO(typePrice);
        }
        return null;
    }
    public TypePrice toEntity(TypePriceDTO typePriceDTO) {
        TypePrice typePrice = new TypePrice();
        typePrice.setId(typePriceDTO.getId());
        typePrice.setName(typePriceDTO.getName());
        typePrice.setStatus(typePriceDTO.getStatus());
        return typePrice;
    }
    //Vi luc insert id = null nen se ko set truong Id

    public TypePriceDTO toDTO(TypePrice typePrice) {
        TypePriceDTO typePriceDTO = commonTransferData(typePrice);
        typePriceDTO.setId(typePrice.getId());
        return typePriceDTO;
    }

//    public TypeRoomDTO toDTOWhenInsert(TypeRoom typeRoom) {
//        return commonTransferData(typeRoom);
//    }

    public TypePriceDTO commonTransferData(TypePrice typePrice) {
        TypePriceDTO typePriceDTO = new TypePriceDTO();
        typePriceDTO.setName(typePrice.getName());
        typePriceDTO.setStatus(typePrice.getStatus());
        return typePriceDTO;
    }
}
