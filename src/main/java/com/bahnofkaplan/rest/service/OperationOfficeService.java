package com.bahnofkaplan.rest.service;

import com.bahnofkaplan.rest.data.entity.OperationOffice;
import com.bahnofkaplan.rest.data.repository.IOperationOfficeRepositoryCSV;
import com.bahnofkaplan.rest.data.repository.IOperationOfficeRepositoryDB;
import com.bahnofkaplan.rest.dto.OperationOfficeDTO;
import com.bahnofkaplan.rest.utility.TrackExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class OperationOfficeService implements IOperationOfficeService {

    Logger logger= LoggerFactory.getLogger(OperationOfficeService.class);
    private final IOperationOfficeRepositoryCSV iOperationOfficeRepositoryCSV;
    private final IOperationOfficeRepositoryDB iOperationOfficeRepositoryDB;

    public OperationOfficeService(@Qualifier("operationOfficeRepositoryCSV") IOperationOfficeRepositoryCSV iOperationOfficeRepositoryCSV, IOperationOfficeRepositoryDB iOperationOfficeRepositoryDB) {
        this.iOperationOfficeRepositoryCSV = iOperationOfficeRepositoryCSV;
        this.iOperationOfficeRepositoryDB = iOperationOfficeRepositoryDB;
    }

    @Override
    @TrackExecutionTime
    public OperationOfficeDTO getOperationOfficeByCodeFromCSV(String code) throws IOException {
        logger.info("now we are inside of the method called getOperationOfficeByCode : {}", LocalDateTime.now());
        return iOperationOfficeRepositoryCSV.getOperationOfficeByCodeFromCSV(code);
    }

    @Override
    @TrackExecutionTime
    public OperationOfficeDTO getOperationOfficeByCodeFromCollection(String code) throws IOException {
        logger.info("now we are inside of the method called getOperationOfficeByCode : {}", LocalDateTime.now());
        return iOperationOfficeRepositoryCSV.getOperationOfficeByCodeFromCollection(code);
    }

    @Override
    @TrackExecutionTime
    public OperationOfficeDTO getOperationOfficeByCodeAdmin(String code) throws IOException {
        iOperationOfficeRepositoryCSV.getOperationOfficesFromCSV().stream()
                .filter(oo -> Objects.equals(oo.getCode(), code.toUpperCase(Locale.ROOT)))
                .forEach(iOperationOfficeRepositoryDB::save);
        return iOperationOfficeRepositoryCSV.getOperationOfficeByCodeFromCSV(code);
    }

    @Override
    @TrackExecutionTime
    public List<OperationOffice> getOperationOffices() {
        return iOperationOfficeRepositoryDB.findAll();
    }
}
