package com.bahnofkaplan.rest.data.repository;

import com.bahnofkaplan.rest.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.name = ?1")
    Optional<Role> findByName(String name);


}