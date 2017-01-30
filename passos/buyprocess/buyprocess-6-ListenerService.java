package com.br.choppcloud.buyprocess.processar;

import java.io.IOException;

import com.br.choppcloud.buyprocess.bank.PagamentoRetorno;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.br.choppcloud.buyprocess.bank.BankService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ListenerService {

	@Autowired
	private BankService bank;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${fila.entrada}")
	private String nomeFilaRepublicar;

	@Value("${fila.finalizado}")
	private String nomeFilaFinalizado;

	@HystrixCommand(fallbackMethod = "republicOnMessage")
	@RabbitListener(queues="${fila.entrada}")
    public void onMessage(Message message) throws JsonParseException, JsonMappingException, IOException  {
		
		String json = new String(message.getBody(), "UTF-8");
		
		System.out.println("Mensagem recebida:"+json);
		
		ObjectMapper mapper = new ObjectMapper();
		CompraChaveJson compraChaveJson = mapper.readValue(json, CompraChaveJson.class);

		PagamentoRetorno pg = bank.pagar(compraChaveJson);

		CompraFinalizadaJson compraFinalizadaJson = new CompraFinalizadaJson();
		compraFinalizadaJson.setCompraChaveJson(compraChaveJson);
		compraFinalizadaJson.setPagamentoOK(pg.isPagamentoOK());
		compraFinalizadaJson.setMensagem(pg.getMensagem());

		String jsonFinalizado = new String(new JsonMessageConverter().toMessage(compraFinalizadaJson, null).getBody(), "UTF-8");
		rabbitTemplate.convertAndSend(nomeFilaFinalizado, jsonFinalizado);
    }

	public void republicOnMessage(Message message) throws JsonParseException, JsonMappingException, IOException  {
		System.out.println("Republicando mensagem...");
		rabbitTemplate.convertAndSend(nomeFilaRepublicar, message);
	}
}