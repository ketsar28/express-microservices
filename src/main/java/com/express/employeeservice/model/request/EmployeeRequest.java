package com.express.employeeservice.model.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmployeeRequest {
    private String id;
    private String name;
    private String email;
    private String bloodGroup;
}