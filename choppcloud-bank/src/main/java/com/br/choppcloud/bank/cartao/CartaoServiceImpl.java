package com.br.choppcloud.bank.cartao;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoServiceImpl implements CartaoService{

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Override
	public boolean isValido(Integer codigoSegurancaCartao, Integer nroCartao) {
		return cartaoRepository.findCartaoValido(codigoSegurancaCartao, nroCartao) > 0;
	}

	@Override
	public boolean isSaldoSuficiente(Integer codigoSegurancaCartao,
			Integer nroCartao, BigDecimal valorCompra) {
		return cartaoRepository.isSaldoSuficiente(codigoSegurancaCartao, nroCartao, valorCompra) > 0;
	}

	@Override
	public Cartao getCartao(Integer codigoSegurancaCartao, Integer nroCartao) {
		return cartaoRepository.findCartao(codigoSegurancaCartao, nroCartao);
	}

	@Transactional
	@Override
	public void atualizarSaldo(Integer codigoSegurancaCartao,
			Integer nroCartao, BigDecimal valorCompra) {
		cartaoRepository.atualizarSaldo(codigoSegurancaCartao, nroCartao, valorCompra);
	}

}
