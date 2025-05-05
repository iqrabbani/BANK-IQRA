package com.assesment.bank.dto.nasabah;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NasabahNewDto{

    private String nomorKtp;

    private String namaLengkap;

    private String nomorHp;

    private String alamat;

    private String tempatLahir;

    private LocalDate tanggalLahir;
}
