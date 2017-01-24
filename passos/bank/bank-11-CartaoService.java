package com.br.choppcloud.bank.cartao;

import java.math.BigDecimal;

public interface CartaoService {

	boolean isValido(Integer codigoSegurancaCartao, Integer nroCartao);

	boolean isSaldoSuficiente(Integer codigoSegurancaCartao, Integer nroCartao,
			BigDecimal valorCompra);

	Cartao getCartao(Integer codigoSegurancaCartao, Integer nroCartao);

	void atualizarSaldo(Integer codigoSegurancaCartao, Integer nroCartao,
			BigDecimal valorCompra);

}
