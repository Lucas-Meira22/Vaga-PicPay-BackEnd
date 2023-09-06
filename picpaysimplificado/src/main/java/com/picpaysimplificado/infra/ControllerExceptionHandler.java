package com.picpaysimplificado.infra;

import com.picpaysimplificado.dtos.ExeptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExeptionDTO exeptionDTO = new ExeptionDTO("User already has been created","400");
        return ResponseEntity.badRequest().body(exeptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralEntry(Exception exception){
        ExeptionDTO exeptionDTO = new ExeptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exeptionDTO);
    }


}
