package com.deutschebahn.rest.data.repository;

import com.deutschebahn.rest.data.entity.OperationOffice;
import com.deutschebahn.rest.dto.OperationOfficeDTO;

import java.io.IOException;
import java.util.Set;


public interface IOperationOfficeRepositoryCSV {
    OperationOfficeDTO getOperationOfficeByCodeFromCSV(String code) throws IOException;

    OperationOfficeDTO getOperationOfficeByCodeFromCollection(String code);

    Set<OperationOffice> getOperationOfficesFromCSV();

    Set<OperationOffice> getOperationOfficesFromCollection();
}
