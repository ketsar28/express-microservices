package com.express.addressapp.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AddressResponse {
    private String id;
    private String lane1;
    private String lane2;
    private String state;
    private String zip;
    private String employeeId;
}
