package com.deutschebahn.rest.data.repository;

import com.deutschebahn.rest.data.entity.OperationOffice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IOperationOfficeRepositoryDB extends JpaRepository<OperationOffice, Long> {

}