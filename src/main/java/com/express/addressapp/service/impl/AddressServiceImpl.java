package com.express.addressapp.service.impl;

import com.express.addressapp.entity.Address;
import com.express.addressapp.model.response.AddressResponse;
import com.express.addressapp.repository.AddressRepository;
import com.express.addressapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    @Override
    public AddressResponse getAddressById(Integer addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address with id " + addressId + " is not found"));

//        return modelMapper.map(address, AddressResponse.class);
            AddressResponse response = new AddressResponse();
            BeanUtils.copyProperties(address, response);
            return response;
    }

    @Override
    public AddressResponse getAddressByEmployeeId(Integer employeeId) {
        Address address = addressRepository.findAddressByEmployeeId(employeeId);
        System.out.println("request for get address with employe id : " + employeeId);
        return modelMapper.map(address, AddressResponse.class);
//        AddressResponse response = new AddressResponse();
//        BeanUtils.copyProperties(address, response);
//        return response;
    }

    @Override
    public List<AddressResponse> getAllAddress() {
        List<Address> addresses = addressRepository.findAll();
        System.out.println("request for get all address");
        List<AddressResponse> addressResponses = Arrays.asList(modelMapper.map(addresses, AddressResponse[].class));
        return addressResponses;
    }
}
