package com.bahnofkaplan.rest.service;

import com.bahnofkaplan.rest.data.entity.OperationOffice;
import com.bahnofkaplan.rest.dto.OperationOfficeDTO;

import java.io.IOException;
import java.util.List;

public interface IOperationOfficeService {

//    OperationOfficeDTO getOperationOfficeByCode(String code) throws IOException;

    OperationOfficeDTO getOperationOfficeByCodeFromCSV(String code) throws IOException;

    OperationOfficeDTO getOperationOfficeByCodeFromCollection(String code) throws IOException;

    OperationOfficeDTO getOperationOfficeByCodeAdmin(String code) throws IOException;

    List<OperationOffice> getOperationOffices();
}
