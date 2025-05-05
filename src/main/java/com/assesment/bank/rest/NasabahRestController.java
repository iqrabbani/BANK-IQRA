package com.assesment.bank.rest;

import com.assesment.bank.dto.nasabah.NasabahDetailDto;
import com.assesment.bank.dto.nasabah.NasabahNewDto;
import com.assesment.bank.dto.nasabah.NasabahUpdateDto;
import com.assesment.bank.service.NasabahService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nasabah-api")
public class NasabahRestController{
    private final NasabahService service;

    @Autowired
    public NasabahRestController(NasabahService service){
        this.service = service;
    }

    @GetMapping("detail")
    public ResponseEntity<Object> detail(
            @RequestParam(required = false) String nomorRekening
    ){
        NasabahDetailDto dto = service.detail(nomorRekening);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("save-new")
    public ResponseEntity<Object> saveNew(
            @Valid @RequestBody NasabahNewDto dto,
            BindingResult bindingResult){
        try {
            if (!bindingResult.hasErrors()){
                service.saveNew(dto);
                var bodies = dto.getNomorKtp() + " berhasil disave";
                return ResponseEntity.status(HttpStatus.OK).body(bodies);
            }else {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(bindingResult.getAllErrors());
            }
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("RUNTIME ERROR");
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<Object> delete(@RequestParam(name = "nomorRekening") String nomorRekening){
        try {
            service.delete(nomorRekening);
            return ResponseEntity.status(HttpStatus.OK).
                    body("Shipment " + nomorRekening + " berhasil dihapus");
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("RUNTIME ERROR");
        }
    }

    @PostMapping("save-update")
    public ResponseEntity<Object> saveUpdate(
            @Valid @RequestBody NasabahUpdateDto dto,
            BindingResult bindingResult){
        try {
            if (!bindingResult.hasErrors()){
                service.saveUpdate(dto);
                var bodies = dto.getNomorRekening() + " berhasil diupdate";
                return ResponseEntity.status(HttpStatus.OK).body(bodies);
            }else {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(bindingResult.getAllErrors());
            }
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("RUNTIME ERROR");
        }
    }

    @GetMapping("ktp-search")
    public ResponseEntity<Object> search(
            @RequestParam(required = false) String nomorKtp
    ){
        var dto = service.getAllByKtp(nomorKtp);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}

