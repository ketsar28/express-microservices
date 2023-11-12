package com.express.addressapp.controller;

import com.express.addressapp.model.response.AddressResponse;
import com.express.addressapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getAddressByEmployeeId(@PathVariable("employeeId") Integer employeeId) {
        AddressResponse address = addressService.getAddressByEmployeeId(employeeId);
        return ResponseEntity.ok(address);
    }

    @GetMapping
    public ResponseEntity<?> getAllAddress() {
        List<AddressResponse> addresses = addressService.getAllAddress();
        return ResponseEntity.ok(addresses);
    }

}
