package com.express.addressapp.service;

import com.express.addressapp.model.response.AddressResponse;

public interface AddressService {
    AddressResponse getAddressById(Integer addressId);
    AddressResponse getAddressByEmployeeId(Integer employeeId);
}
