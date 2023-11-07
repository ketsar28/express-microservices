package com.express.employeeservice.service.impl;

import com.express.employeeservice.entity.Employee;
import com.express.employeeservice.model.response.EmployeeResponse;
import com.express.employeeservice.repository.EmployeeRepository;
import com.express.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeResponse getEmployeeById(Integer employeeId){
       Optional<Employee> employee = employeeRepository.findById(employeeId);
       if(employee.isPresent()) {
           return modelMapper.map(employee, EmployeeResponse.class);
       } else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "employee with id " + employeeId + " is not found");
       }
    }
}
