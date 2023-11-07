package com.express.addressapp.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommonResponse<T> {
    private T data;
    private String errors;
    private Integer statusCode;


    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public T getData(T data) {
        return data;
    }

    @JsonProperty("errors")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getErrors(String errors) {
        return errors;
    }

    @JsonProperty("statusCode")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getStatusCode(Integer statusCode) {
        return statusCode;
    }

}
