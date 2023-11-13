package com.express.employeeservice.model.response;

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
