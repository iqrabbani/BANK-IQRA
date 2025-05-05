package com.assesment.bank.dto.nasabah;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NasabahUpdateDto{

    private String nomorRekening;

    private String nomorHp;

    private String alamat;

}
