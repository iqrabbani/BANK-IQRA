package com.assesment.bank.dto.nasabah;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NasabahDetailDto{

    private String nomorRekening;

    private String nomorKtp;

    private String namaLengkap;

    private String nomorHp;

    private String alamat;

    private String tempatLahir;

    private LocalDate tanggalLahir;

    private LocalDate tanggalDaftar;
}
