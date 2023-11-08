package com.express.addressapp.service;

import com.express.addressapp.model.response.AddressResponse;
import java.util.List;

public interface AddressService {
    AddressResponse getAddressById(Integer addressId);
    AddressResponse getAddressByEmployeeId(Integer employeeId);
    List<AddressResponse> getAllAddress();
}
