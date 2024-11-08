package com.digitallabs.tci_assessment.exception;

import com.digitallabs.tci_assessment.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDTO> resourceNotFoundException(ResourceNotFoundException ex){
        String msg = ex.getMessage();
        ResponseDTO resp = new ResponseDTO(msg, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceDuplicateException.class)
    public ResponseEntity<ResponseDTO> resourceDuplicateException(ResourceDuplicateException ex){
        String msg = ex.getMessage();
        ResponseDTO resp = new ResponseDTO(msg, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body("Invalid Argument Received");
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> handleDateTimeParserException(DateTimeParseException ex){
        return ResponseEntity.badRequest().body("Date could not be parsed correctly");
    }
}
