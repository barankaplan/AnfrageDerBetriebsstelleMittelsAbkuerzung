package com.bahnofkaplan.rest.mapper;


import com.bahnofkaplan.rest.data.entity.OperationOffice;
import com.bahnofkaplan.rest.dto.OperationOfficeDTO;
import org.mapstruct.Mapper;


@Mapper(implementationName = "OperationOfficeInfoMapper", componentModel = "spring")
public interface IOperationOfficeMapper {
    OperationOffice toOperationOffice(OperationOfficeDTO operationOfficeDTO);

    OperationOfficeDTO toOperationOfficeDTO(OperationOffice operationOffice);

}
