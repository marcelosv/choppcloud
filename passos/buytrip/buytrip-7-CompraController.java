package com.br.choppcloud.buytrip.compra;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController {

	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Value("${compra.queue}")
	private String nomeFila;
	
	@RequestMapping(path = "/compra", method = RequestMethod.POST)
	public ResponseEntity<RetornoJson> pagamento(
			@Valid @NotNull @RequestBody CompraJson compraJson) throws MessageConversionException, UnsupportedEncodingException {

		CompraChaveJson compraChaveJson = new CompraChaveJson();
		compraChaveJson.setCompraJson(compraJson);
		compraChaveJson.setChave(UUID.randomUUID().toString());
		
		String json = new String(new JsonMessageConverter().toMessage(compraChaveJson, null).getBody(), "UTF-8");
		
		rabbitTemplate.convertAndSend(nomeFila, json);
		
		RetornoJson retorno = new RetornoJson();
		retorno.setMensagem("Compra registrada com sucesso. Aguarda a confirmação do pagamento.");
		retorno.setChavePesquisa(compraChaveJson.getChave());
		
		return new ResponseEntity<RetornoJson>(retorno, HttpStatus.OK);
	}
	
}