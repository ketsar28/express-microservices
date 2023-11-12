package com.express.employeeservice.openfeignclients;

import com.express.employeeservice.model.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api")
public interface AddressClient {
    @GetMapping("/addresses/{employeeId}")
    ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") Integer employeeId);

    @GetMapping("/addresses")
    ResponseEntity<List<AddressResponse>> getAllAddress();
}
