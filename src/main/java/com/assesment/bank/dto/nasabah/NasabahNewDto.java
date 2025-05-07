package com.assesment.bank.dto.nasabah;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NasabahNewDto{

    @NotBlank
    @Size(max = 16)
    private String nomorKtp;

    @NotBlank
    @Size(max = 50)
    private String namaLengkap;

    @NotBlank
    @Size(max = 15)
    private String nomorHp;

    @NotBlank
    @Size(max = 255)
    private String alamat;

    @NotBlank
    @Size(max = 20)
    private String tempatLahir;

    @Past
    private LocalDate tanggalLahir;
}
