package com.express.employeeservice.controller;

import com.express.employeeservice.entity.Employee;
import com.express.employeeservice.model.response.CommonResponse;
import com.express.employeeservice.model.response.EmployeeResponse;
import com.express.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") Integer employeeId) {
        EmployeeResponse employee = employeeService.getEmployeeById(employeeId);
        CommonResponse<?> response = CommonResponse.builder()
                .data(employee)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

}
