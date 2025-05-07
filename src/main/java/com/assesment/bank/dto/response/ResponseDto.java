package com.assesment.bank.dto.response;

import lombok.Data;

@Data
public class ResponseDto{
    private String statusCode;
    private Boolean status;
    private String message;
    private Object data;
}
