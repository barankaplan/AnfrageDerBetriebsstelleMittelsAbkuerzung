package com.bahnofkaplan.rest.data.dal;


import com.bahnofkaplan.rest.data.entity.OperationOffice;
import com.bahnofkaplan.rest.data.exception.ResourceNotFoundException;
import com.bahnofkaplan.rest.dto.OperationOfficeDTO;
import com.bahnofkaplan.rest.mapper.IOperationOfficeMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Component
public class OperationOfficeApplicationDAL {
    private final IOperationOfficeMapper iOperationOfficeMapper;

    public OperationOfficeApplicationDAL(IOperationOfficeMapper iOperationOfficeMapper) {
        this.iOperationOfficeMapper = iOperationOfficeMapper;
    }

    private OperationOfficeDTO getOperationOfficeDTO(String code, Optional<OperationOffice> operationOfficeOptional) {
        return operationOfficeOptional.map(iOperationOfficeMapper::toOperationOfficeDTO).orElseThrow(() -> new ResourceNotFoundException("OperationOffice", "code", code));
    }

    public OperationOfficeDTO getOperationOfficeByCodeFromCSV(String code) {
        Optional<OperationOffice> operationOffice = OperationOfficeFactory.getOperationOfficeByCodeFromCSV(code);
        assert Objects.requireNonNull(operationOffice).isPresent();
        return getOperationOfficeDTO(code, operationOffice);
    }

    public OperationOfficeDTO getOperationOfficeByCodeFromCollection(String code) {
        Optional<OperationOffice> operationOffice = OperationOfficeFactory.getOperationOfficeByCodeFromCollection(code);
        assert Objects.requireNonNull(operationOffice).isPresent();
        return getOperationOfficeDTO(code, operationOffice);
    }

    public Set<OperationOffice> getOperationOfficesFromCSV() {
        return OperationOfficeFactory.getOperationOfficesLoadFromCSVFile();
    }


    public Set<OperationOffice> getOperationOfficesFromCollection() {
        return OperationOfficeFactory.getOperationOfficesLoadFromCollectionOnce();
    }


}
