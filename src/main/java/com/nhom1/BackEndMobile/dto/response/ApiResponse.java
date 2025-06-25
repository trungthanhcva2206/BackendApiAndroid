package com.nhom1.BackEndMobile.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T>{
    int code = 200;
    String message;
    T result;
    public ApiResponse(T result) {
        this.result = result;
    }
}
