package com.express.employeeservice.service;

import com.express.employeeservice.model.request.EmployeeRequest;
import com.express.employeeservice.model.request.UpdateEmployeeRequest;
import com.express.employeeservice.model.request.UpdateAddressByEmployeeRequest;
import com.express.employeeservice.model.response.EmployeeResponse;
import java.util.List;

public interface EmployeeService {
    EmployeeResponse getEmployeeById(String employeeId);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse createNewEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse updateEmployeeById(UpdateEmployeeRequest employeeRequest);
    EmployeeResponse updateEmployeeWithAddress(UpdateAddressByEmployeeRequest employeeAndAddressRequest);
    void deleteEmployeeById(String employeeId);
}
