package com.express.employeeservice.service.impl;

import com.express.employeeservice.entity.Employee;
import com.express.employeeservice.model.request.EmployeeRequest;
import com.express.employeeservice.model.request.UpdateEmployeeRequest;
import com.express.employeeservice.model.request.UpdateAddressByEmployeeRequest;
import com.express.employeeservice.model.response.AddressResponse;
import com.express.employeeservice.model.response.EmployeeResponse;
import com.express.employeeservice.openfeignclients.AddressClient;
import com.express.employeeservice.repository.EmployeeRepository;
import com.express.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final AddressClient addressClient;

    @Override
    public EmployeeResponse createNewEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .id(employeeRequest.getId())
                .email(employeeRequest.getEmail())
                .name(employeeRequest.getName())
                .bloodGroup(employeeRequest.getBloodGroup())
                .build();

        employeeRepository.saveAndFlush(employee);

        return modelMapper.map(employee, EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse updateEmployeeById(UpdateEmployeeRequest employeeRequest) {
        Employee existingEmployee = employeeRepository.findById(employeeRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found"));
        if (Objects.nonNull(existingEmployee)) {
            existingEmployee.setBloodGroup(employeeRequest.getBloodGroup());
            existingEmployee.setEmail(employeeRequest.getEmail());
            existingEmployee.setName(employeeRequest.getName());

            employeeRepository.save(existingEmployee);

            EmployeeResponse employeeResponse = modelMapper.map(existingEmployee, EmployeeResponse.class);
            List<AddressResponse> addressResponses = addressClient.getAllAddress().getBody();

            List<AddressResponse> addressList = addressResponses.stream()
                    .filter(address -> employeeResponse.getId().equals(address.getEmployeeId()))
                    .collect(Collectors.toList());

            employeeResponse.setAddressResponse(addressList);
        }
        return null;
    }

    @Override
    public EmployeeResponse updateEmployeeWithAddress(UpdateAddressByEmployeeRequest employeeAndAddressRequest) {
        Employee existingEmployee = employeeRepository.findById(employeeAndAddressRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        existingEmployee.setId(existingEmployee.getId());
        existingEmployee.setBloodGroup(employeeAndAddressRequest.getBloodGroup());
        existingEmployee.setEmail(employeeAndAddressRequest.getEmail());
        existingEmployee.setName(employeeAndAddressRequest.getName());

        employeeRepository.save(existingEmployee);

        EmployeeResponse employeeResponse = modelMapper.map(existingEmployee, EmployeeResponse.class);

        // Update the address using addressClient
        AddressResponse addressResponse = addressClient.updateAddressByEmployeeId(employeeAndAddressRequest).getBody();

        employeeResponse.setAddressResponse(List.of(addressResponse));

        return employeeResponse;
    }

    @Override
    public void deleteEmployeeById(String employeeId) {
        List<AddressResponse> addressResponse = addressClient.getAddressByEmployeeId(employeeId).getBody();

        if(addressResponse.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found");
        }


        String deleteAddressMessage = addressClient.deleteAddressByEmployeeId(employeeId).getBody();
        System.out.println("address message : " + deleteAddressMessage);


        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeResponse> employeeResponses = Arrays.asList(modelMapper.map(employees, EmployeeResponse[].class));

        employeeResponses.forEach(employeeResponse -> {
            List<AddressResponse> addressList = new ArrayList<>();
            for (AddressResponse addressResponse : addressClient.getAllAddress().getBody()) {
                if (addressResponse.getEmployeeId().equals(employeeResponse.getId())) {
                    addressList.add(addressResponse);
                }

                employeeResponse.setAddressResponse(addressList);
            }

        });
        return employeeResponses;
    }


    @Override
    public EmployeeResponse getEmployeeById(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get(); // db call => 10 seconds (ex)

        // casting to employee response object
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        List<AddressResponse> addressResponse = addressClient.getAddressByEmployeeId(employeeId).getBody();

        employeeResponse.setAddressResponse(addressResponse);


        return employeeResponse;
    }
}
