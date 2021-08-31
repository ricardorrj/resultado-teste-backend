package com.ricardorrj.cadastroProcesso.resources.exception;

import com.ricardorrj.cadastroProcesso.service.exception.CadastroException;
import com.ricardorrj.cadastroProcesso.service.exception.DataIntegrityViolationException;
import com.ricardorrj.cadastroProcesso.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResorceExceptionHandler {

    @ExceptionHandler(CadastroException.class)
    public ResponseEntity<StandardError> CadastroException(CadastroException e, ServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, ServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(), e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<StandardError>> MethodArgumentNotValidException(MethodArgumentNotValidException e, ServletRequest request){

        List<StandardError> test = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(test);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
