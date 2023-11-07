package com.express.addressapp.service.impl;

import com.express.addressapp.entity.Address;
import com.express.addressapp.model.response.AddressResponse;
import com.express.addressapp.repository.AddressRepository;
import com.express.addressapp.service.AddressService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
//    private final ModelMapper modelMapper;
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
        Address address = addressRepository.findAddressByEmployeeId(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address with employee id " + employeeId + " is not found"));

//        return modelMapper.map(address, AddressResponse.class);
        AddressResponse response = new AddressResponse();
        BeanUtils.copyProperties(address, response);
        return response;
    }
}
