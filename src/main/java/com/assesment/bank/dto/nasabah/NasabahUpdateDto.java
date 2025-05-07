package com.assesment.bank.dto.nasabah;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NasabahUpdateDto{

    @NotBlank
    private String nomorRekening;

    @NotBlank
    private String nomorHp;

    @NotBlank
    private String alamat;

}
