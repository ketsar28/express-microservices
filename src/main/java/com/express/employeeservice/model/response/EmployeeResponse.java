package com.express.employeeservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmployeeResponse {
    private String id;
    private String name;
    private String email;
    private String bloodGroup;
    private List<AddressResponse> addressResponse;

    @JsonProperty("addressResponse")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<AddressResponse> getAddressResponse() {
        return addressResponse;
    }
}
