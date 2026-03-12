package com.jadeilton.agendador_tarefas_back_and.infrastructure.exceptions;

public class ResourceNotFoundException extends RuntimeException {



    public ResourceNotFoundException(String messagem){
        super(messagem);
    }




    public ResourceNotFoundException(String messangem, Throwable throwable){
        super(messangem, throwable);
    }
}
