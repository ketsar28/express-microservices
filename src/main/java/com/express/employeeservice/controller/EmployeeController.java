package com.express.employeeservice.controller;

import com.express.employeeservice.model.request.EmployeeRequest;
import com.express.employeeservice.model.request.UpdateEmployeeRequest;
import com.express.employeeservice.model.request.UpdateAddressByEmployeeRequest;
import com.express.employeeservice.model.response.CommonResponse;
import com.express.employeeservice.model.response.EmployeeResponse;
import com.express.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") String employeeId) {
        EmployeeResponse employee = employeeService.getEmployeeById(employeeId);
        CommonResponse<?> response = CommonResponse.builder()
                .data(employee)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
       List<EmployeeResponse> employeeResponses = employeeService.getAllEmployees();
       CommonResponse<?> response = CommonResponse.builder()
               .data(employeeResponses)
               .build();

       return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<?> createNewEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employee = employeeService.createNewEmployee(employeeRequest);
        CommonResponse<?> response = CommonResponse.builder()
                .data(employee)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateEmployeeById(@RequestBody UpdateEmployeeRequest employeeRequest) {
        EmployeeResponse employee = employeeService.updateEmployeeById(employeeRequest);
        CommonResponse<?> response = CommonResponse.builder()
                .data(employee)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployeeWithAddress(@RequestBody UpdateAddressByEmployeeRequest employeeRequest) {
        EmployeeResponse employee = employeeService.updateEmployeeWithAddress(employeeRequest);
        CommonResponse<?> response = CommonResponse.builder()
                .data(employee)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("employeeId") String employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        String data = "employee with id " + employeeId + " has been deleted";
        CommonResponse<?> response = CommonResponse.builder().data(data).build();
        return ResponseEntity.ok(response);
    }
}
