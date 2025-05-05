package com.assesment.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "nasabah")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nasabah{
    @Id
    @Column(name = "no_rekening", length = 20)
    private String nomorRekening;

    @Column(name = "no_ktp", length = 20, nullable = false)
    private String nomorKtp;

    @Column(name = "nama_lengkap", length = 100, nullable = false)
    private String namaLengkap;

    @Column(name = "no_hp", length = 15, nullable = false)
    private String nomorHp;

    @Column(name = "alamat", nullable = false)
    private String alamat;

    @Column(name = "tempat_lahir", length = 50, nullable = false)
    private String tempatLahir;

    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDate tanggalLahir;

    @Column(name = "tanggal_daftar", nullable = false)
    private LocalDate tanggalDaftar;
}
