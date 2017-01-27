package com.br.choppcloud.buyprocess.bank;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.choppcloud.buyprocess.processar.CompraChaveJson;

@Service
public class BankService {

	@Value("${bank.link}")
	private String link;
	
	public void pagar(CompraChaveJson compraChaveJson) {
		
		PagamentoJson json = new PagamentoJson();
		json.setNroCartao(compraChaveJson.getCompraJson().getNroCartao());
		json.setCodigoSegurancaCartao(compraChaveJson.getCompraJson().getCodigoSegurancaCartao());
		json.setValorCompra(compraChaveJson.getCompraJson().getValorPassagem());
		
		try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> vars = new HashMap<String, String>();
            BankRetornoJson bankRetorno = restTemplate.postForObject(link, json, BankRetornoJson.class, vars);
        }catch (RuntimeException ex){
            ex.printStackTrace();
        }
		
	}

}
