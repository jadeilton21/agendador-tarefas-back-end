package com.jadeilton.agendador_tarefas_back_and.infrastructure.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {




    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResouceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> HandlerUnauthorizedException(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
