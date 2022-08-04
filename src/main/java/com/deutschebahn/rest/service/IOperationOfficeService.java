package com.deutschebahn.rest.service;

import com.deutschebahn.rest.data.entity.OperationOffice;
import com.deutschebahn.rest.dto.OperationOfficeDTO;

import java.io.IOException;
import java.util.List;

public interface IOperationOfficeService {

//    OperationOfficeDTO getOperationOfficeByCode(String code) throws IOException;

    OperationOfficeDTO getOperationOfficeByCodeFromCSV(String code) throws IOException;

    OperationOfficeDTO getOperationOfficeByCodeFromCollection(String code) throws IOException;

    OperationOfficeDTO getOperationOfficeByCodeAdmin(String code) throws IOException;

    List<OperationOffice> getOperationOffices();
}
