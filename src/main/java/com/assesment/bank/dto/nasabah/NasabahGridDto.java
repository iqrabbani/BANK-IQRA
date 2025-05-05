package com.assesment.bank.dto.nasabah;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NasabahGridDto{

    private String nomorRekening;

    private String nomorKtp;

    private String namaLengkap;

    private String tanggalDaftar;

}
