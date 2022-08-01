package com.deutschebahn.rest.data.repository;

import com.deutschebahn.rest.data.entity.OperationOffice;
import com.deutschebahn.rest.dto.OperationOfficeDTO;

import java.io.IOException;
import java.util.Set;


public interface IOperationOfficeRepositoryCSV {
    OperationOfficeDTO getOperationOfficeByCode(String code) throws IOException;

    Set<OperationOffice> getOperationOffices();
}
