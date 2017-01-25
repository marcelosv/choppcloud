package com.br.choppcloud.buytrip;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandlerController {

  /*  @ExceptionHandler(PagamentoException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RetornoJson process(RuntimeException ex) {
        return new RetornoJson(ex.getMessage());
    }
    */
}
