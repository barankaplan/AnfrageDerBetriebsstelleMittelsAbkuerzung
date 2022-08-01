package com.deutschebahn.rest.service;

import com.deutschebahn.rest.data.entity.OperationOffice;
import com.deutschebahn.rest.data.repository.IOperationOfficeRepositoryCSV;
import com.deutschebahn.rest.data.repository.IOperationOfficeRepositoryDB;
import com.deutschebahn.rest.dto.OperationOfficeDTO;
import com.deutschebahn.rest.utility.TrackExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
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
    public OperationOfficeDTO getOperationOfficeByCode(String code) throws IOException {
        logger.info("now we are inside of the method called getOperationOfficeByCode : {}", LocalDateTime.now());
        return iOperationOfficeRepositoryCSV.getOperationOfficeByCode(code);
    }

    @Override
    @TrackExecutionTime
    public OperationOfficeDTO getOperationOfficeByCodeAdmin(String code) throws IOException {
        iOperationOfficeRepositoryCSV.getOperationOffices().stream()
                .filter(oo -> Objects.equals(oo.getCode(), code.toUpperCase(Locale.ROOT)))
                .forEach(iOperationOfficeRepositoryDB::save);
        return iOperationOfficeRepositoryCSV.getOperationOfficeByCode(code);
    }

    @Override
    @TrackExecutionTime
    public List<OperationOffice> getOperationOffices() {
        return iOperationOfficeRepositoryDB.findAll();
    }
}
