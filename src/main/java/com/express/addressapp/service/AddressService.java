package com.express.addressapp.service;

import com.express.addressapp.model.request.AddressRequest;
import com.express.addressapp.model.request.UpdateAddressByEmployeeRequest;
import com.express.addressapp.model.request.UpdateAddressRequest;
import com.express.addressapp.model.response.AddressResponse;
import java.util.List;

public interface AddressService {
    AddressResponse getAddressById(String addressId);
    List<AddressResponse> getAddressByEmployeeId(String employeeId);
    List<AddressResponse> getAllAddress();
    AddressResponse createNewAddress(AddressRequest addressRequest);
    AddressResponse updateAddressById(UpdateAddressRequest addressRequest);
    AddressResponse updateAddressByEmployeeId(UpdateAddressByEmployeeRequest addressRequest);
    void deleteAddressByEmployeeId(String employeeId);
}
