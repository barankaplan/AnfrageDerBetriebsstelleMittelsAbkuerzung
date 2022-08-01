package com.deutschebahn.rest.mapper;


import com.deutschebahn.rest.data.entity.OperationOffice;
import com.deutschebahn.rest.dto.OperationOfficeDTO;
import org.mapstruct.Mapper;


@Mapper(implementationName = "OperationOfficeInfoMapper", componentModel = "spring")
public interface IOperationOfficeMapper {
    OperationOffice toOperationOffice(OperationOfficeDTO operationOfficeDTO);

    OperationOfficeDTO toOperationOfficeDTO(OperationOffice operationOffice);

}
