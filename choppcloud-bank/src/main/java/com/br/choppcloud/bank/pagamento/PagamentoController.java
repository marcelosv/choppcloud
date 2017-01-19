package com.br.choppcloud.bank.pagamento;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentoController {

	@RequestMapping(path = "/pagamento", method = RequestMethod.POST)
	public ResponseEntity<RetornoJson> pagamento(
			@Valid @NotNull @RequestBody PagamentoJson pagamentoJson) {

		RetornoJson retorno = new RetornoJson();
		return new ResponseEntity<RetornoJson>(retorno, HttpStatus.OK);
	}
}
