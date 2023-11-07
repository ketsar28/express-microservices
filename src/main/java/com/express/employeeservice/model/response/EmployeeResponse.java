package com.express.employeeservice.model.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmployeeResponse {
    private Integer id;
    private String name;
    private String email;
    private String bloodGroup;
}
