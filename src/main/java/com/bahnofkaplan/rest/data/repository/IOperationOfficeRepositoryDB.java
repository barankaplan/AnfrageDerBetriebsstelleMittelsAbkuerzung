package com.bahnofkaplan.rest.data.repository;

import com.bahnofkaplan.rest.data.entity.OperationOffice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IOperationOfficeRepositoryDB extends JpaRepository<OperationOffice, Long> {

}