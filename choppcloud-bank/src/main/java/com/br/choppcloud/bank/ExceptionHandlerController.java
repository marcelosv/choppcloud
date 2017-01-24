package com.br.choppcloud.bank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.br.choppcloud.bank.pagamento.PagamentoException;
import com.br.choppcloud.bank.pagamento.RetornoJson;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(PagamentoException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RetornoJson process(RuntimeException ex) {
        return new RetornoJson(ex.getMessage());
    }
    
}
