package com.assesment.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindingResultResponseDto{

    private String objectName;
    private String field;
    private String rejectedValue;
    private String errorMessage;

}
