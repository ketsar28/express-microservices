package com.express.addressapp.repository;

import com.express.addressapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(value = "select ma.id, ma.lane_1, ma.lane_2, ma.state, ma.zip from m_address ma join m_employee me on ma.employee_id = me.id where ma.employee_id = :employeeId ", nativeQuery = true)
    Optional<Address> findAddressByEmployeeId(@Param("employeeId") Integer employeeId);
}
