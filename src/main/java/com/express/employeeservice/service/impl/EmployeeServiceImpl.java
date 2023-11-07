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

@Service
//@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    // // this variable can't read first, 'cause constructor is read in advance when this service is created

    // @Value("${addressservice.base.url}")
    // private String BASE_URL;


    public EmployeeServiceImpl(
            @Value("${addressservice.base.url}") String BASE_URL,
            EmployeeRepository employeeRepository,
            ModelMapper modelMapper,
            RestTemplateBuilder builder) {

        System.out.println("base url : " + BASE_URL);
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = builder
                .rootUri(BASE_URL)
                .build();
        // FYI : RestTemplate Builder => spring creates object for this autowired it
    }

    @Override
    public EmployeeResponse getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get(); // db call => 10 seconds (ex)
        // casting to employee response object
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

        // addressResponse - set data by making a rest api call
        String uri = "/addresses/" + employeeId;

        AddressResponse addressResponse = restTemplate.getForObject(uri, AddressResponse.class); // external rest api call => 10 - 15 seconds (ex)
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }
}


//        ResponseEntity<CommonResponse<AddressResponse>> addressResponseEntity = restTemplate.exchange(
//                uri,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<CommonResponse<AddressResponse>>() {});
//
//        if (addressResponseEntity.getStatusCode() == HttpStatus.OK) {
//            AddressResponse addressResponse = addressResponseEntity.getBody().getData();
//            employeeResponse.setAddressResponse(addressResponse);
//        }