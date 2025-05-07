package com.assesment.bank.service;

import com.assesment.bank.dto.nasabah.NasabahDetailDto;
import com.assesment.bank.dto.nasabah.NasabahGridDto;
import com.assesment.bank.dto.nasabah.NasabahNewDto;
import com.assesment.bank.dto.nasabah.NasabahUpdateDto;
import com.assesment.bank.dto.nasabah.response.NasabahDeleteResponseDto;
import com.assesment.bank.dto.nasabah.response.NasabahNewResponseDto;
import com.assesment.bank.dto.nasabah.response.NasabahUpdateResponseDto;
import com.assesment.bank.entity.Nasabah;
import com.assesment.bank.repository.NasabahRepository;
import com.assesment.bank.utility.FormatterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    public NasabahNewResponseDto saveNew(NasabahNewDto dto){
        Nasabah nasabah = new Nasabah();

        String nomorRekening = "";
        boolean isAvailableRekening = false;

        Random random = new Random();

        do {
            nomorRekening = "0898" + dto.getNomorKtp().substring(6,10) +
                    String.format("%04d", random.nextInt(10000));
            if(repository.countNoRekening(nomorRekening)==0){
                isAvailableRekening = true;
            }
        } while (!isAvailableRekening);

        nasabah.setNomorRekening(nomorRekening);
        nasabah.setNomorKtp(dto.getNomorKtp());
        nasabah.setNamaLengkap(dto.getNamaLengkap());
        nasabah.setAlamat(dto.getAlamat());
        nasabah.setNomorHp(dto.getNomorHp());
        nasabah.setTempatLahir(dto.getTempatLahir());
        nasabah.setTanggalLahir(dto.getTanggalLahir());
        nasabah.setTanggalDaftar(LocalDate.now());

        repository.save(nasabah);

        return new NasabahNewResponseDto(nasabah.getNomorRekening(),
                nasabah.getNomorKtp(),nasabah.getNamaLengkap(),nasabah.getNomorHp(),
                nasabah.getAlamat(),nasabah.getTempatLahir(),nasabah.getTanggalLahir(),
                nasabah.getTanggalDaftar()
        );
    }

    public NasabahUpdateResponseDto saveUpdate(NasabahUpdateDto dto){
        try {
            Nasabah nasabah = repository.findById(dto.getNomorRekening()).orElseThrow();
            nasabah.setAlamat(dto.getAlamat());
            nasabah.setNomorHp(dto.getNomorHp());
            repository.save(nasabah);

            return new NasabahUpdateResponseDto(dto.getNomorRekening(),dto.getNomorHp(),dto.getAlamat(),LocalDate.now());

        } catch (Exception exception){
            return null;
        }
    }

    public NasabahDetailDto detail(String nomorRekening){
        try {
            var nasabah = repository.findById(nomorRekening).orElseThrow();
            return new NasabahDetailDto(
                    nasabah.getNomorRekening(),nasabah.getNomorKtp(),
                    nasabah.getNamaLengkap(),nasabah.getNomorHp(),nasabah.getAlamat(),
                    nasabah.getTempatLahir(),nasabah.getTanggalLahir(),nasabah.getTanggalDaftar()
            );
        } catch (Exception exception){
            return null;
        }
    }

    public NasabahDeleteResponseDto delete(String nomorRekening){
        try {
            var nasabah = repository.findById(nomorRekening).orElseThrow();
            repository.deleteById(nomorRekening);
            return new NasabahDeleteResponseDto(
                    nasabah.getNomorRekening(),nasabah.getNomorKtp(),nasabah.getNamaLengkap()
            );

        } catch (Exception exception){
            return null;
        }
    }
}
