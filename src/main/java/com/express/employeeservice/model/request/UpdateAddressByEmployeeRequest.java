package com.express.employeeservice.model.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateAddressByEmployeeRequest {
    private String id;
    private String name;
    private String email;
    private String bloodGroup;
    //    private UpdateAddressByEmployeeRequest updateAddress;
    private String lane1;
    private String lane2;
    private String state;
    private String zip;
}
