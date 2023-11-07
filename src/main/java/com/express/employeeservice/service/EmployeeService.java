package com.express.employeeservice.service;

import com.express.employeeservice.model.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse getEmployeeById(Integer employeeId);
}
