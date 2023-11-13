package com.express.employeeservice.model.request;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateAddressByEmployeeRequestSecond {
    private String id;
    private String lane1;
    private String lane2;
    private String state;
    private String zip;
    private String employeeId;
}
