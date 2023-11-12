package com.express.employeeservice.service;

import com.express.employeeservice.model.response.EmployeeResponse;
import java.util.List;

public interface EmployeeService {
    EmployeeResponse getEmployeeById(Integer employeeId);
    List<EmployeeResponse> getAllEmployees();
}
