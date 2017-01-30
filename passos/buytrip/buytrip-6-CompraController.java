package com.br.choppcloud.buytrip.compra;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController {

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<RetornoJson> pagamento(
			@Valid @NotNull @RequestBody CompraJson compraJson) {

		RetornoJson retorno = new RetornoJson();
		retorno.setMensagem("Compra registrada com sucesso. Aguarda a confirmação do pagamento.");
		
		return new ResponseEntity<RetornoJson>(retorno, HttpStatus.OK);
	}
	
}
