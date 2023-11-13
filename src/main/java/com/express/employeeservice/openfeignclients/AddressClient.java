package com.express.employeeservice.openfeignclients;

import com.express.employeeservice.model.request.UpdateAddressByEmployeeRequest;
import com.express.employeeservice.model.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api")
public interface AddressClient {
    @GetMapping("/addresses/{employeeId}")
    ResponseEntity<List<AddressResponse>> getAddressByEmployeeId(@PathVariable("employeeId") String employeeId);

    @GetMapping("/addresses")
    ResponseEntity<List<AddressResponse>> getAllAddress();

    @PutMapping("/addresses/update")
    ResponseEntity<AddressResponse> updateAddressByEmployeeId(@RequestBody UpdateAddressByEmployeeRequest addressRequest);

    @DeleteMapping("/addresses/{employeeId}")
    ResponseEntity<String> deleteAddressByEmployeeId(@PathVariable("employeeId") String employeeId);

}
