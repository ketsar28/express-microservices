package com.express.employeeservice.service.impl;

import com.express.employeeservice.entity.Employee;
import com.express.employeeservice.feignclient.AddressClient;
import com.express.employeeservice.model.response.AddressResponse;
import com.express.employeeservice.model.response.EmployeeResponse;
import com.express.employeeservice.repository.EmployeeRepository;
import com.express.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final AddressClient addressClient;

//    @Autowired
//    private EmployeeRepository employeeRepository;
//    @Autowired
//    private ModelMapper modelMapper;
//    @Autowired
//    private AddressClient addressClient;


    @Override
    public EmployeeResponse getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get(); // db call => 10 seconds (ex)
        // casting to employee response object
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

        // addressResponse - set data by making a rest api call -- feign client way
        AddressResponse addressResponse = addressClient.getAddressByEmployeeId(employeeId);

//        // addressResponse - set data by making a rest api call -- running in background of feign client
//        ResponseEntity<AddressResponse> addressResponseEntity = addressClient.getAddressByEmployeeId(employeeId);
//        AddressResponse addressResponse = addressResponseEntity.getBody();

        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponseList = employees.stream().map(employee -> modelMapper.map(employee, EmployeeResponse.class)).collect(Collectors.toList());

        // addressResponse - set data by making a rest api call -- feign client way
        List<AddressResponse> clientAllAddress = addressClient.getAllAddress();
        for (EmployeeResponse employeeResponse : employeeResponseList) {
            for (AddressResponse allAddress : clientAllAddress) {
                employeeResponse.setAddressResponse(allAddress);
            }
        }
        return employeeResponseList;
    }


}
