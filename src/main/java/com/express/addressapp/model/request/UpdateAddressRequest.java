package com.express.addressapp.model.request;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateAddressRequest {
    private String id;
    private String lane1;
    private String lane2;
    private String state;
    private String zip;
}
