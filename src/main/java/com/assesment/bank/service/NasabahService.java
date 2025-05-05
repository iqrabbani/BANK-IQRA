package com.assesment.bank.service;

import com.assesment.bank.dto.nasabah.NasabahDetailDto;
import com.assesment.bank.dto.nasabah.NasabahGridDto;
import com.assesment.bank.dto.nasabah.NasabahNewDto;
import com.assesment.bank.dto.nasabah.NasabahUpdateDto;
import com.assesment.bank.entity.Nasabah;
import com.assesment.bank.repository.NasabahRepository;
import com.assesment.bank.utility.FormatterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class NasabahService{
    private final NasabahRepository repository;

    @Autowired
    public NasabahService(NasabahRepository repository){
        this.repository = repository;
    }

    public List<NasabahGridDto> getAll(){
        var nasabahList = repository.findAll();
        var nasabahGrid = new LinkedList<NasabahGridDto>();

        for(var nas: nasabahList){
            var addNas = new NasabahGridDto();
            addNas.setNomorRekening(nas.getNomorRekening());
            addNas.setNamaLengkap(nas.getNamaLengkap());
            addNas.setNomorKtp(nas.getNomorKtp());
            addNas.setTanggalDaftar(FormatterHelper.formatDate(nas.getTanggalDaftar()));
            nasabahGrid.add(addNas);
        }
        return nasabahGrid;
    }

    public List<NasabahGridDto> getAllByKtp(String noKtp){
        var nasabahList = repository.getAllByKtp(noKtp);
        var nasabahGrid = new LinkedList<NasabahGridDto>();

        for(var nas: nasabahList){
            var addNas = new NasabahGridDto();
            addNas.setNomorRekening(nas.getNomorRekening());
            addNas.setNamaLengkap(nas.getNamaLengkap());
            addNas.setNomorKtp(nas.getNomorKtp());
            addNas.setTanggalDaftar(FormatterHelper.formatDate(nas.getTanggalDaftar()));
            nasabahGrid.add(addNas);
        }
        return nasabahGrid;
    }

    public void saveNew(NasabahNewDto dto){
        Nasabah nasabah = new Nasabah();

        String nomorRekening = "0898" +
                dto.getNomorKtp().substring(dto.getNomorKtp().length() - 5) +
                dto.getNomorHp().substring(dto.getNomorHp().length() - 5);

        nasabah.setNomorRekening(nomorRekening);
        nasabah.setNomorKtp(dto.getNomorKtp());
        nasabah.setNamaLengkap(dto.getNamaLengkap());
        nasabah.setAlamat(dto.getAlamat());
        nasabah.setNomorHp(dto.getNomorHp());
        nasabah.setTempatLahir(dto.getTempatLahir());
        nasabah.setTanggalLahir(dto.getTanggalLahir());
        nasabah.setTanggalDaftar(LocalDate.now());

        repository.save(nasabah);
    }

    public void saveUpdate(NasabahUpdateDto dto){
        Nasabah nasabah = repository.findById(dto.getNomorRekening()).orElseThrow();
        if (!dto.getAlamat().trim().equalsIgnoreCase("") || dto.getAlamat() != null){
            nasabah.setAlamat(dto.getAlamat());
        }
        if (!dto.getNomorHp().trim().equalsIgnoreCase("") || dto.getNomorHp() != null){
            nasabah.setNomorHp(dto.getNomorHp());
        }

        repository.save(nasabah);
    }

    public NasabahDetailDto detail(String nomorRekening){
        var nasabah = repository.findById(nomorRekening).orElseThrow();
        return new NasabahDetailDto(
                nasabah.getNomorRekening(),nasabah.getNomorKtp(),
                nasabah.getNamaLengkap(),nasabah.getNomorHp(),nasabah.getAlamat(),
                nasabah.getTempatLahir(),nasabah.getTanggalLahir(),nasabah.getTanggalDaftar()
        );
    }

    public void delete(String nomorRekening){
        repository.deleteById(nomorRekening);
    }
}
