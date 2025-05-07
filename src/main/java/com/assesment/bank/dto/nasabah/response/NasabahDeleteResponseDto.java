package com.assesment.bank.dto.nasabah.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NasabahDeleteResponseDto{
    private String nomorRekening;

    private String nomorKtp;

    private String namaLengkap;
}
