package com.express.addressapp.service.impl;

import com.express.addressapp.entity.Address;
import com.express.addressapp.model.request.AddressRequest;
import com.express.addressapp.model.request.UpdateAddressByEmployeeRequest;
import com.express.addressapp.model.request.UpdateAddressRequest;
import com.express.addressapp.model.response.AddressResponse;
import com.express.addressapp.repository.AddressRepository;
import com.express.addressapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddressResponse createNewAddress(AddressRequest addressRequest) {
        Address address = Address.builder()
                .id(addressRequest.getId())
                .lane1(addressRequest.getLane1())
                .lane2(addressRequest.getLane2())
                .state(addressRequest.getState())
                .zip(addressRequest.getZip())
                .employeeId(addressRequest.getEmployeeId())
                .build();

        addressRepository.saveAndFlush(address);

        return modelMapper.map(address, AddressResponse.class);
    }

    @Override
    public AddressResponse updateAddressById(UpdateAddressRequest addressRequest) {
        Address existingAddress = addressRepository.findById(addressRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found"));
        existingAddress.setZip(addressRequest.getZip());
        existingAddress.setState(addressRequest.getState());
        existingAddress.setLane1(addressRequest.getLane1());
        existingAddress.setLane2(addressRequest.getLane2());

        addressRepository.save(existingAddress);
        return modelMapper.map(existingAddress, AddressResponse.class);
    }

    @Override
    public AddressResponse updateAddressByEmployeeId(UpdateAddressByEmployeeRequest addressRequest) {
        List<Address> existingAddress = addressRepository.findAddressByEmployeeId(addressRequest.getId());

        for (Address address : existingAddress) {
            address.setId(address.getId());
            address.setZip(addressRequest.getZip());
            address.setState(addressRequest.getState());
            address.setLane1(addressRequest.getLane1());
            address.setLane2(addressRequest.getLane2());
            address.setEmployeeId(addressRequest.getId());

            addressRepository.save(address);

            return modelMapper.map(existingAddress, AddressResponse.class);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "address with employee id " + addressRequest.getId() + " not found");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAddressByEmployeeId(String employeeId) {
        List<Address> addresses = addressRepository.findAddressByEmployeeId(employeeId);

        if (addresses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found");
        }
        addressRepository.deleteAddressByEmployeeId(employeeId);
    }

    @Override
    public AddressResponse getAddressById(String addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address with id " + addressId + " is not found"));

//        return modelMapper.map(address, AddressResponse.class);
        AddressResponse response = new AddressResponse();
        BeanUtils.copyProperties(address, response);
        return response;
    }

    @Override
    public List<AddressResponse> getAddressByEmployeeId(String employeeId) {
        List<Address> addresses = addressRepository.findAddressByEmployeeId(employeeId);

        if(addresses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found");
        }
        System.out.println("request for get address with employe id : " + employeeId);

        return Arrays.asList(modelMapper.map(addresses, AddressResponse[].class));
    }

    @Override
    @SneakyThrows // dont use this annotation in production mode
    public List<AddressResponse> getAllAddress() {
        log.info("wait started");
        Thread.sleep(7000);
        log.info("wait ended");
        List<Address> addresses = addressRepository.findAll();
        System.out.println("request for get all address");
        List<AddressResponse> addressResponses = Arrays.asList(modelMapper.map(addresses, AddressResponse[].class));
        return addressResponses;
    }
}
