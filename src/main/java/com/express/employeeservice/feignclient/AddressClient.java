package com.express.employeeservice.feignclient;

import com.express.employeeservice.model.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// http://localhost:8082/address-app/api/addresses/2

/*
*  example : http://localhost:8080/coba-app/api/coba-coba
* http://localhost:8080 : server host
* /coba-app/api : context path / path
* /coba-coba : endpoint
*
* name = "nama_dari_aplikasi_microservice_tersebut" / namanya pun bebas juga boleh
* */

@FeignClient(name = "address-service", url = "http://localhost:8082", path = "/address-app/api")
public interface AddressClient { // proxy (server)

    // feign client way
    @GetMapping("/addresses/{employeeId}")
    AddressResponse getAddressByEmployeeId(@PathVariable("employeeId") Integer employeeId);

//      // running in background of feign client
//      @GetMapping("/addresses/{employeeId}")
//      public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") Integer employeeId);

      // feign client way
      @GetMapping("/addresses")
      List<AddressResponse> getAllAddress();

//    // running in background of feign client
//    @GetMapping
//    public ResponseEntity<List<AddressResponse>> getAllAddress();
}
