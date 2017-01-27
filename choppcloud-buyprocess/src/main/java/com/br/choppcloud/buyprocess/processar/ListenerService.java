package com.br.choppcloud.buyprocess.processar;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.choppcloud.buyprocess.bank.BankService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ListenerService {

	@Autowired
	private BankService bank;

	@RabbitListener(queues="${spring.cloud.stream.bindings.input.destination}.${spring.cloud.stream.bindings.input.group}")
    public void onMessage(Message message) throws JsonParseException, JsonMappingException, IOException  {
		
		String json = new String(message.getBody(), "UTF-8");
		
		System.out.println("mensagem recebida:"+json);
		
		ObjectMapper mapper = new ObjectMapper();
		CompraChaveJson compraChaveJson = mapper.readValue(json, CompraChaveJson.class);

		bank.pagar(compraChaveJson);
    }
    
}