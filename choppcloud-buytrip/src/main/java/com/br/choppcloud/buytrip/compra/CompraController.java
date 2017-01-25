package com.br.choppcloud.buytrip.compra;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CompraController {

	private static final String COMPRA = "COMPRA";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping(path = "/compra", method = RequestMethod.POST)
	public ResponseEntity<RetornoJson> pagamento(
			@Valid @NotNull @RequestBody CompraJson compraJson) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(compraJson);
		
		stringRedisTemplate.convertAndSend(COMPRA, json);
		
		RetornoJson retorno = new RetornoJson();
		retorno.setMensagem("Compra registrada com sucesso. Aguarda a confirmação do pagamento.");
		
		return new ResponseEntity<RetornoJson>(retorno, HttpStatus.OK);
	}
	
}
