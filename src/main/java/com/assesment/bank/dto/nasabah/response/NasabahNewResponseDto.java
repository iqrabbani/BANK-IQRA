package com.assesment.bank.dto.nasabah.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NasabahNewResponseDto{

    private String nomorRekening;

    private String nomorKtp;

    private String namaLengkap;

    private String nomorHp;

    private String alamat;

    private String tempatLahir;

    private LocalDate tanggalLahir;

    private LocalDate tanggalDaftar;

}
