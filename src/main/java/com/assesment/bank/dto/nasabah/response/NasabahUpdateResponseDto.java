package com.assesment.bank.dto.nasabah.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NasabahUpdateResponseDto{

    private String nomorRekening;

    private String nomorHp;

    private String alamat;

    private LocalDate tanggalUpdate;

}
