package com.ecommerce.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class BaseResponse {

    private Integer responseCode;

    private String responseMessage;

    private Object response;

    private String status;

    public BaseResponse() {
        this.responseCode = HttpStatus.OK.value();
        this.responseMessage = HttpStatus.OK.getReasonPhrase();
        this.response = HttpStatus.OK;
        this.status = "SUCCESS";
    }

    public BaseResponse(Object response) {
        this.response = response;
    }

    public BaseResponse(Integer responseCode, String responseMessage, String status) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.status = status;
    }
}
