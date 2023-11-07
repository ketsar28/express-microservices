package com.express.employeeservice.service.impl;

import com.express.employeeservice.entity.Employee;
import com.express.employeeservice.model.response.AddressResponse;
import com.express.employeeservice.model.response.EmployeeResponse;
import com.express.employeeservice.repository.EmployeeRepository;
import com.express.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final WebClient webClient;


    @Override
    public EmployeeResponse getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get(); // db call => 10 seconds (ex)
        // casting to employee response object
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

        // addressResponse - set data by making a rest api call
        String uri = "/addresses/" + employeeId;

        AddressResponse addressResponse = webClient.get()
                .uri("/addresses/" + employeeId)
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();

        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }

}
