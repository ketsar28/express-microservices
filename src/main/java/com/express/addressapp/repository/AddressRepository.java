package com.express.addressapp.repository;

import com.express.addressapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    List<Address> findAddressByEmployeeId(String employeeId);
    void deleteAddressByEmployeeId(String employeeId);
//    @Query(value = "select ma.id, ma.lane_1, ma.lane_2, ma.state, ma.zip from m_address ma join m_employee me on ma.employee_id = me.id where ma.employee_id = :employeeId", nativeQuery = true)
//    Address findAddressByEmployeeId(@Param("employeeId") String employeeId);

//    @Modifying
//    @Query(value = "INSERT INTO m_address (id, lane_1, lane_2, state, zip, employee_id) " +
//            "SELECT ma.lane_1, ma.lane_2, ma.state, ma.zip, ma.employee_id " +
//            "FROM m_address ma JOIN m_employee me ON ma.employee_id = me.id " +
//            "WHERE ma.employee_id = :employeeId", nativeQuery = true)
//    void insertAddressByEmployeeId(@Param("employeeId") String employeeId);

//    @Query(value = "delete from m_address ma where ma.employee_id = :employeeId" , nativeQuery = true)
//    void deleteAddressByEmployeeId(@Param("employeeId") String employeeId);

}
