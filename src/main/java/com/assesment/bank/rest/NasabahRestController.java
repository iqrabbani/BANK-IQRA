package com.assesment.bank.rest;

import com.assesment.bank.dto.nasabah.NasabahNewDto;
import com.assesment.bank.dto.nasabah.NasabahUpdateDto;
import com.assesment.bank.dto.response.BindingResultResponseDto;
import com.assesment.bank.dto.response.ResponseDto;
import com.assesment.bank.service.NasabahService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/nasabah-api")
public class NasabahRestController{
    private final NasabahService service;

    @Autowired
    public NasabahRestController(NasabahService service){
        this.service = service;
    }

    @GetMapping("all-nasabah")
    public ResponseEntity<Object> findALl(){
        ResponseDto responseDto = new ResponseDto();
        try {
            var dto = service.getAll();
            if(dto.size()>0){
                responseDto.setStatusCode("000");
                responseDto.setStatus(true);
                responseDto.setMessage("Success Get Data");
                responseDto.setData(dto);
            } else {
                responseDto.setStatusCode("001");
                responseDto.setStatus(true);
                responseDto.setMessage("Success Get Data (Empty)");
                responseDto.setData(dto);
            }
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception exception){
            responseDto.setStatusCode("005");
            responseDto.setStatus(false);
            responseDto.setMessage("Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @GetMapping("detail")
    public ResponseEntity<Object> detail(
            @RequestParam(required = false) String nomorRekening
    ){
        boolean isCorrect = nomorRekening != null;
        ResponseDto responseDto = new ResponseDto();
        try {
            var dto = service.detail(nomorRekening);
            if (dto != null && isCorrect) {
                responseDto.setStatusCode("000");
                responseDto.setStatus(true);
                responseDto.setMessage("Success Get Data");
                responseDto.setData(dto);
                return ResponseEntity.status(HttpStatus.OK).body(responseDto);
            } else if (isCorrect){
                responseDto.setStatusCode("001");
                responseDto.setStatus(true);
                responseDto.setMessage("Success (No Data Found)");
                responseDto.setData(dto);
                return ResponseEntity.status(HttpStatus.OK).body(responseDto);
            } else {
                responseDto.setStatusCode("003");
                responseDto.setStatus(false);
                responseDto.setMessage("Failed Get Parameter");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseDto);
            }
        } catch (Exception exception) {
            responseDto.setStatusCode("005");
            responseDto.setStatus(false);
            responseDto.setMessage("Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @PostMapping("save-new")
    public ResponseEntity<Object> saveNew(
            @Valid @RequestBody NasabahNewDto nasabahNewDto,
            BindingResult bindingResult){
        ResponseDto responseDto = new ResponseDto();
        try {
            if (!bindingResult.hasErrors()){
                var dto  = service.saveNew(nasabahNewDto);
                responseDto.setStatusCode("000");
                responseDto.setStatus(true);
                responseDto.setMessage("Success Post New Data");
                responseDto.setData(dto);
                return ResponseEntity.status(HttpStatus.OK).body(responseDto);
            }else {
                responseDto.setStatusCode("002");
                responseDto.setStatus(false);
                responseDto.setMessage("Form is Incomplete");
                var bindingResultDtoList = new LinkedList<BindingResultResponseDto>();
                {
                    for(ObjectError bind: bindingResult.getAllErrors()){
                        var bindingResultDto = new BindingResultResponseDto();
                        bindingResultDto.setObjectName(bind.getObjectName());
                        if (bind instanceof FieldError fieldError){
                            bindingResultDto.setField(fieldError.getField());
                            bindingResultDto.setRejectedValue(fieldError.getRejectedValue().toString());
                        }
                        bindingResultDto.setErrorMessage(bind.getDefaultMessage());
                        bindingResultDtoList.add(bindingResultDto);
                    }
                }
                responseDto.setData(bindingResultDtoList);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseDto);
            }
        }catch (Exception ex){
            responseDto.setStatusCode("005");
            responseDto.setStatus(false);
            responseDto.setMessage("Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<Object> delete(@RequestParam(required = false) String nomorRekening){
        boolean isCorrect = nomorRekening != null;
        ResponseDto responseDto = new ResponseDto();
        try {
            var dto  = service.delete(nomorRekening);
            if (dto != null && isCorrect) {
                responseDto.setStatusCode("000");
                responseDto.setStatus(true);
                responseDto.setMessage("Success Delete Data");
                responseDto.setData(dto);
                return ResponseEntity.status(HttpStatus.OK).body(responseDto);
            } else if (isCorrect){
                responseDto.setStatusCode("001");
                responseDto.setStatus(false);
                responseDto.setMessage("Failed Delete Data");
                responseDto.setData(dto);
                return ResponseEntity.status(HttpStatus.OK).body(responseDto);
            } else {
                responseDto.setStatusCode("003");
                responseDto.setStatus(false);
                responseDto.setMessage("Failed Get Parameter");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseDto);
            }
        }catch (Exception ex){
            responseDto.setStatusCode("005");
            responseDto.setStatus(false);
            responseDto.setMessage("Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @PostMapping("save-update")
    public ResponseEntity<Object> saveUpdate(
            @Valid @RequestBody NasabahUpdateDto nasabahUpdateDto,
            BindingResult bindingResult){
        ResponseDto responseDto = new ResponseDto();
        try {
            if (!bindingResult.hasErrors()){
                var dto  = service.saveUpdate(nasabahUpdateDto);
                responseDto.setStatusCode("000");
                responseDto.setStatus(true);
                responseDto.setMessage("Success Update New Data");
                responseDto.setData(dto);
                return ResponseEntity.status(HttpStatus.OK).body(responseDto);
            }else {
                responseDto.setStatusCode("002");
                responseDto.setStatus(false);
                responseDto.setMessage("Form is Incomplete");
                var bindingResultDtoList = new LinkedList<BindingResultResponseDto>();
                {
                    for(ObjectError bind: bindingResult.getAllErrors()){
                        var bindingResultDto = new BindingResultResponseDto();
                        bindingResultDto.setObjectName(bind.getObjectName());
                        if (bind instanceof FieldError fieldError){
                            bindingResultDto.setField(fieldError.getField());
                            bindingResultDto.setRejectedValue(fieldError.getRejectedValue().toString());
                        }
                        bindingResultDto.setErrorMessage(bind.getDefaultMessage());
                        bindingResultDtoList.add(bindingResultDto);
                    }
                }
                responseDto.setData(bindingResultDtoList);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseDto);
            }
        }catch (Exception ex){
            responseDto.setStatusCode("005");
            responseDto.setStatus(false);
            responseDto.setMessage("Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @GetMapping("ktp-search")
    public ResponseEntity<Object> search(
            @RequestParam(required = false) String nomorKtp
    ){
        boolean isCorrect = nomorKtp != null;
        ResponseDto responseDto = new ResponseDto();
        try {
            var dto = service.getAllByKtp(nomorKtp);
            if(dto.size()>0 && isCorrect){
                responseDto.setStatusCode("000");
                responseDto.setStatus(true);
                responseDto.setMessage("Success Get Data");
                responseDto.setData(dto);
                return ResponseEntity.status(HttpStatus.OK).body(responseDto);
            } else if(dto.size()==0 && isCorrect){
                responseDto.setStatusCode("000");
                responseDto.setStatus(true);
                responseDto.setMessage("Success (No Data Found)");
                responseDto.setData(dto);
                return ResponseEntity.status(HttpStatus.OK).body(responseDto);
            } else {
                responseDto.setStatusCode("003");
                responseDto.setStatus(false);
                responseDto.setMessage("Failed Get Parameter");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseDto);
            }
        } catch (Exception exception){
            responseDto.setStatusCode("005");
            responseDto.setStatus(false);
            responseDto.setMessage("Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }
}

