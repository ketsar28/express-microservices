package com.express.addressapp.openfeignclient;

import com.express.addressapp.model.response.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@FeignClient(name = "employee-service", path = "/employee-app/api")
public interface EmployeeClient {
    @GetMapping("/employees/{employeeId}")
    ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("employeeId") String employeeId);

    @GetMapping("/employees")
    ResponseEntity<List<EmployeeResponse>> getAllEmployees();
}
