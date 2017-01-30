package com.br.choppcloud.buyfeedback;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.br.choppcloud.buyfeedback.finalizar.NaoFinalizadoException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NaoFinalizadoException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String process(NaoFinalizadoException ex) {
        return "Compra ainda não finalizada.";
    }
    
}
