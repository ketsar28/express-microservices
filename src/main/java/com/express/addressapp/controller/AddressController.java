package com.express.addressapp.controller;

import com.express.addressapp.model.request.AddressRequest;
import com.express.addressapp.model.request.UpdateAddressByEmployeeRequest;
import com.express.addressapp.model.request.UpdateAddressRequest;
import com.express.addressapp.model.response.AddressResponse;
import com.express.addressapp.model.response.CommonResponse;
import com.express.addressapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getAddressByEmployeeId(@PathVariable("employeeId") String employeeId) {
        List<AddressResponse> address = addressService.getAddressByEmployeeId(employeeId);
        return ResponseEntity.ok(address);
    }

    @GetMapping
    public ResponseEntity<?> getAllAddress() {
        List<AddressResponse> addresses = addressService.getAllAddress();
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    public ResponseEntity<?> createNewAddress(@RequestBody AddressRequest addressRequest) {
        AddressResponse address = addressService.createNewAddress(addressRequest);
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(address)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateAddressById(@RequestBody UpdateAddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.updateAddressById(addressRequest);
        CommonResponse<?> response = CommonResponse.builder()
                .data(addressResponse)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAddressByEmployeeId(@RequestBody UpdateAddressByEmployeeRequest addressRequest) {
        AddressResponse addressResponse = addressService.updateAddressByEmployeeId(addressRequest);
        CommonResponse<?> response = CommonResponse.builder()
                .data(addressResponse)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteAddressByEmployeeId(@PathVariable("employeeId") String employeeId) {
        addressService.deleteAddressByEmployeeId(employeeId);
        String data = "address with employee id " + employeeId + " has been deleted";
        CommonResponse<?> response = CommonResponse.builder().data(data).build();
        return ResponseEntity.ok(response);
    }

}
