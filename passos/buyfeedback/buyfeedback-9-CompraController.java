package com.br.choppcloud.buyfeedback.finalizar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController {

	@Autowired
	private CompraRedisRepository compraRedisRepository;
	
	@RequestMapping(path = "/{chave}", method = RequestMethod.GET)
	public CompraRedis status(@PathVariable("chave") String chave){
		
		CompraRedis compra = compraRedisRepository.findOne(chave);
		
		if( compra == null ){
			throw new NaoFinalizadoException();
		}
		
		return compra;
	}
}
