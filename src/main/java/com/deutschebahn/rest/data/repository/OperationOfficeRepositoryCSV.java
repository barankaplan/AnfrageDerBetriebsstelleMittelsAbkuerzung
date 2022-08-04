package com.deutschebahn.rest.data.repository;


import com.deutschebahn.rest.data.dal.OperationOfficeApplicationDAL;
import com.deutschebahn.rest.data.entity.OperationOffice;
import com.deutschebahn.rest.dto.OperationOfficeDTO;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public class OperationOfficeRepositoryCSV implements IOperationOfficeRepositoryCSV {
    private final OperationOfficeApplicationDAL operationOfficeApplicationDAL;

    public OperationOfficeRepositoryCSV(OperationOfficeApplicationDAL operationOfficeApplicationDAL) {
        this.operationOfficeApplicationDAL = operationOfficeApplicationDAL;
    }

    @Override
    public OperationOfficeDTO getOperationOfficeByCodeFromCSV(String code) {
        return operationOfficeApplicationDAL.getOperationOfficeByCodeFromCSV(code);
    }

    @Override
    public OperationOfficeDTO getOperationOfficeByCodeFromCollection(String code) {
        return operationOfficeApplicationDAL.getOperationOfficeByCodeFromCollection(code);
    }

    @Override
    public Set<OperationOffice> getOperationOfficesFromCSV() {
        return operationOfficeApplicationDAL.getOperationOfficesFromCSV();
    }


    @Override
    public Set<OperationOffice> getOperationOfficesFromCollection() {
        return operationOfficeApplicationDAL.getOperationOfficesFromCollection();
    }
}
